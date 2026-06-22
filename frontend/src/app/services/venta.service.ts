import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private api = 'http://192.168.100.59:8080/api/ventas';

  constructor(private http: HttpClient) {}

  crearVenta(venta: any) {
    return this.http.post(this.api, venta);
  }
}