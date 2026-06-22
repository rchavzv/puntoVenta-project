import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private client!: Client;
  private connected = false;

  private onUpdateCallback?: () => void;

  connect(onUpdate: () => void) {

    this.onUpdateCallback = onUpdate;

    // 🔥 Si ya está conectado NO recrees nada
    if (this.client?.active) {
      console.log('♻️ WebSocket ya activo');
      return;
    }

    this.client = new Client({
      webSocketFactory: () =>
        new SockJS('http://192.168.100.59:8080/ws'),

      reconnectDelay: 5000,

      debug: (msg) => console.log('ws:', msg)
    });

    this.client.onConnect = () => {
      console.log('🟢 WebSocket conectado');
      this.connected = true;

      this.client.subscribe('/topic/products', () => {
        console.log('📦 Evento recibido');

        // 🔥 evita llamadas fuera de ciclo Angular
        this.onUpdateCallback?.();
      });
    };

    this.client.onDisconnect = () => {
      console.log('🔴 WebSocket desconectado');
      this.connected = false;
    };

    this.client.onStompError = (frame) => {
      console.error('❌ STOMP error:', frame);
    };

    this.client.activate();
  }

  disconnect() {
    if (this.client?.active) {
      this.client.deactivate();
      this.connected = false;
      console.log('🔌 WebSocket cerrado');
    }
  }

  isConnected() {
    return this.connected;
  }
}