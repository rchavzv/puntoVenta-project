package com.puntoventa.backend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventService {

    private final SimpMessagingTemplate messagingTemplate;

public ProductEventService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
    System.out.println("🔥 ProductEventService INICIALIZADO");
}

    public void notifyProductsUpdated() {
        System.out.println("📡 Enviando evento WebSocket");

        messagingTemplate.convertAndSend("/topic/products", "update");
    }
}