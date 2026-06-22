@Entity
@Table(name = "sys_ventas")
public class SysVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysVentasId;

    private String sysVentasFolio;

    private BigDecimal sysVentasSubtotal;
    private BigDecimal sysVentasTax;
    private BigDecimal sysVentasTotal;

    private String sysVentasPaymentMethod;

    private String sysVentasNotes;

    // getters y setters
}