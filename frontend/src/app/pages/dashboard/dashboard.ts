import { Component } from '@angular/core';
import { Header } from '../../components/header/header';
import { Ventas } from '../../components/ventas/ventas';
import { Inventario } from '../../components/inventario/inventario';
import { Reportes } from '../../components/reportes/reportes';
@Component({
  selector: 'app-dashboard',
  imports: [Header, Ventas, Inventario, Reportes],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {}
