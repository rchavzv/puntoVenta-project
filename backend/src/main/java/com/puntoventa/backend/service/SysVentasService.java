@Service
public class SysVentaService {

    private final SysVentaRepository ventaRepo;
    private final SysVentaDetalleRepository detalleRepo;
    private final SysProductRepository productRepo;
    private final ProductEventService eventService;

    public SysVentaService(
            SysVentaRepository ventaRepo,
            SysVentaDetalleRepository detalleRepo,
            SysProductRepository productRepo,
            ProductEventService eventService
    ) {
        this.ventaRepo = ventaRepo;
        this.detalleRepo = detalleRepo;
        this.productRepo = productRepo;
        this.eventService = eventService;
    }

    @Transactional
    public SysVenta crearVenta(VentaRequest request) {

        // 1. guardar venta
        SysVenta venta = new SysVenta();
        venta.setSysVentasFolio(request.folio);
        venta.setSysVentasSubtotal(request.subtotal);
        venta.setSysVentasTax(request.tax);
        venta.setSysVentasTotal(request.total);
        venta.setSysVentasPaymentMethod(request.metodoPago);
        venta.setSysVentasNotes(request.notas);

        SysVenta savedVenta = ventaRepo.save(venta);

        // 2. detalles
        for (VentaDetalleRequest item : request.productos) {

            SysProduct product = productRepo.findById(item.productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // validar stock
            if (product.getSysProductsStock() < item.cantidad) {
                throw new RuntimeException("Stock insuficiente");
            }

            // descontar stock
            product.setSysProductsStock(
                    product.getSysProductsStock() - item.cantidad
            );

            productRepo.save(product);

            // guardar detalle
            SysVentaDetalle detalle = new SysVentaDetalle();
            detalle.setSysVentasId(savedVenta.getSysVentasId());
            detalle.setSysProductsId(product.getSysProductsId());
            detalle.setSysVentasDetalleQuantity(item.cantidad);
            detalle.setSysVentasDetalleUnitPrice(item.precio);
            detalle.setSysVentasDetalleLineTotal(
                    item.precio.multiply(BigDecimal.valueOf(item.cantidad))
            );

            detalleRepo.save(detalle);
        }

        // 3. WebSocket update stock
        eventService.notifyProductsUpdated();

        return savedVenta;
    }
}