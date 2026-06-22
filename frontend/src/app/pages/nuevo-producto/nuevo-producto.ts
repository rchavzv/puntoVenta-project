import { Component } from '@angular/core';
import { Header } from '../../components/header/header';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nuevo-producto',
  standalone: true,
  imports: [Header, FormsModule],
  templateUrl: './nuevo-producto.html',
  styleUrl: './nuevo-producto.css',
})
export class NuevoProducto {

  producto = {
    sysProductsName: '',
    sysProductsSku: '',
    sysProductsDescription: '',
    sysProductsPurchasePrice: 0,
    sysProductsSalePrice: 0,
    sysProductsTax: 16,
    sysProductsStock: 0,
    sysProductsMinStock: 0
  };

  constructor(private http: HttpClient) {}

  guardarProducto() {
    this.http.post(
      'http://192.168.100.59:8080/api/products',
      this.producto
    ).subscribe({
      next: (res) => {
        console.log('Producto guardado:', res);
        alert('Producto registrado correctamente');
      },
      error: (err) => {
        console.error('Error completo:', err);

        // 👇 mejor que JSON.stringify (evita errores raros)
        alert(err?.message || 'Error al guardar producto');
      }
    });
  }
}