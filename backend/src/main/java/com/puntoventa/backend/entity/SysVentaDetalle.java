@Entity
@Table(name = "sys_ventas_detalle")
public class SysVentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysVentasDetalleId;

    private Long sysVentasId;

    private Long sysProductsId;

    private Integer sysVentasDetalleQuantity;

    private BigDecimal sysVentasDetalleUnitPrice;

    private BigDecimal sysVentasDetalleLineTotal;

    // getters y setters
}