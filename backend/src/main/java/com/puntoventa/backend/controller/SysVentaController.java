@RestController
@RequestMapping("/api/ventas")
public class SysVentaController {

    private final SysVentaService service;

    public SysVentaController(SysVentaService service) {
        this.service = service;
    }

    @PostMapping
    public SysVenta crear(@RequestBody VentaRequest request) {
        return service.crearVenta(request);
    }
}