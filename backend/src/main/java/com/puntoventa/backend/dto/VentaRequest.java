public class VentaRequest {

    public String folio;
    public BigDecimal subtotal;
    public BigDecimal tax;
    public BigDecimal total;
    public String metodoPago;
    public String notas;

    public List<VentaDetalleRequest> productos;
}