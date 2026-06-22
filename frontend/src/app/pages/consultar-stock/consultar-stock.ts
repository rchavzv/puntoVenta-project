import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Header } from '../../components/header/header';
import { WebsocketService } from '../../services/websocket.service';
import { ChangeDetectorRef } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-consultar-stock',
  standalone: true,
  imports: [CommonModule, Header],
  templateUrl: './consultar-stock.html',
  styleUrl: './consultar-stock.css',
})
export class ConsultarStock implements OnInit {

  productos: any[] = [];
  productoSeleccionado: any = null;

constructor(
  private http: HttpClient,
  private ws: WebsocketService,
  private cdr: ChangeDetectorRef
) {}

ngOnInit() {
   console.log('🟢 INIT STOCK');
  this.cargar();

  this.ws.connect(() => {
    console.log('🔄 WebSocket trigger recibido');
    this.cargar();
  });
}

cargar() {
  console.log('📡 llamando backend...');

  this.http.get<any[]>(`${environment.apiUrl}/products`)
    .subscribe({
      next: (res) => {
        console.log('📦 RESPONSE:', res);

        this.productos = res ?? [];

        // 🔥 ESTO ES LO QUE TE FALTA
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('❌ ERROR BACKEND:', err);
      }
    });
}

  seleccionarProducto(p: any) {
    this.productoSeleccionado = p;
  }


  // 👇 NUEVO
  getStockBajo(): number {
    return this.productos.filter(p => p.sysProductsStock <= 5).length;
  }

  getTotalProductos(): number {
    return this.productos.length;
  }
}