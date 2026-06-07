import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inventario',
  imports: [],
  templateUrl: './inventario.html',
  styleUrl: './inventario.css',
})
export class Inventario {
  constructor(private router: Router) {}
  consultarStock() {
    this.router.navigate(['./consultar-stock']);
  }

  nuevoProducto() {
    this.router.navigate(['/nuevo-producto']);
  }
}
