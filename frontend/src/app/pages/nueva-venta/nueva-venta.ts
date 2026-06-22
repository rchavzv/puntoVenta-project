import { Component, OnInit } from '@angular/core';
import { Header } from '../../components/header/header';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VentaService } from '../../services/venta.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-nueva-venta',
  standalone: true,
  imports: [CommonModule, Header, FormsModule],
  templateUrl: './nueva-venta.html',
  styleUrl: './nueva-venta.css',
})
export class NuevaVenta implements OnInit {

  productos: any[] = [];
  productosFiltrados: any[] = [];
  carrito: any[] = [];

  busqueda = '';
  metodoPago = 'EFECTIVO';
  notas = '';

  constructor(
    private ventaService: VentaService,
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.cargarProductos();
  }

  // cargar productos backend
  cargarProductos() {
    this.http.get<any[]>(`${environment.apiUrl}/products`)
      .subscribe({
        next: (res) => {
          console.log('📦 Productos:', res);

          this.productos = res ?? [];

          // mostrar todos al inicio
          this.productosFiltrados = [...this.productos];
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('❌ Error cargando productos:', err);
        }
      });
  }

  // buscar producto
  filtrar() {
    const texto = this.busqueda.toLowerCase();

    if (!texto) {
      this.productosFiltrados = [...this.productos];
      return;
    }

    this.productosFiltrados = this.productos.filter(p =>
      p.sysProductsName.toLowerCase().includes(texto) ||
      p.sysProductsSku.toLowerCase().includes(texto)
    );
  }

  // agregar al carrito
agregarProducto(p: any) {

  const existente = this.carrito.find(x => x.id === p.sysProductsId);

  const cantidadEnCarrito = existente ? existente.cantidad : 0;

  // 🚨 VALIDAR STOCK REAL
  if (cantidadEnCarrito >= p.sysProductsStock) {
    alert('No hay suficiente stock');
    return;
  }

  if (existente) {
    existente.cantidad++;
    this.calcularTotal(existente);
  } else {
    this.carrito.push({
      id: p.sysProductsId,
      nombre: p.sysProductsName,
      sku: p.sysProductsSku,
      precio: p.sysProductsSalePrice,
      cantidad: 1,
      stock: p.sysProductsStock,
      total: p.sysProductsSalePrice
    });
  }
}

  // aumentar/disminuir cantidad
cambiarCantidad(item: any, valor: number) {

  if (valor > 0 && item.cantidad >= item.stock) {
    alert('No hay más stock disponible');
    return;
  }

  item.cantidad += valor;

  if (item.cantidad < 1) item.cantidad = 1;

  this.calcularTotal(item);
}
  // recalcular total linea
  calcularTotal(item: any) {
    item.total = item.precio * item.cantidad;
  }

  // eliminar producto
  eliminar(item: any) {
    this.carrito = this.carrito.filter(
      p => p.id !== item.id
    );
  }

  // subtotal
  get subtotal(): number {
    return this.carrito.reduce(
      (sum, p) => sum + p.total, 0
    );
  }

  // impuestos
  get tax(): number {
    return this.subtotal * 0.16;
  }

  // total final
  get total(): number {
    return this.subtotal + this.tax;
  }

  // guardar venta
  finalizarVenta() {

    if (this.carrito.length === 0) {
      alert('No hay productos');
      return;
    }

    const venta = {
      folio: 'F-' + Date.now(),
      subtotal: this.subtotal,
      tax: this.tax,
      total: this.total,
      metodoPago: this.metodoPago,
      notas: this.notas,

      productos: this.carrito.map(p => ({
        productId: p.id,
        cantidad: p.cantidad,
        precio: p.precio
      }))
    };

    this.ventaService.crearVenta(venta)
      .subscribe({
        next: (res) => {
          console.log('✅ Venta creada:', res);

          alert('Venta finalizada');

          this.carrito = [];
          this.notas = '';
        },

        error: (err) => {
          console.error('❌ Error venta:', err);
          alert('Error al crear venta');
        }
      });
  }
}