package com.example.chat.chat_server.core.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketSender {

    private final SimpMessagingTemplate messagingTemplate;

    public void send(String destination, Object payload) {
        try {
            messagingTemplate.convertAndSend(destination, payload);
            log.info("WebSocket 전송 - dest: {}, payload: {}", destination, payload);
        } catch (Exception e) {
            log.error("WebSocket 전송 실패 - dest: {}, error: {}", destination, e.getMessage());
        }
    }


}

