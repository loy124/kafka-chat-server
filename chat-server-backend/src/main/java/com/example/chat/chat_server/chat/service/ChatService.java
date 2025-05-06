package com.example.chat.chat_server.chat.service;

import com.example.chat.chat_server.chat.dto.ChatMessage;
import com.example.chat.chat_server.chat.infrastructure.kafka.ChatKafkaProducer;
import com.example.chat.chat_server.core.websocket.WebSocketSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatKafkaProducer chatKafkaProducer;
    private final WebSocketSender webSocketSender;


    public void sendMessage(ChatMessage chatMessage) {
        chatKafkaProducer.send(chatMessage);
    }

    public void handleReceivedMessage(ChatMessage message) {
        // 1. DB 저장 로직 (선택, 지금은 생략)

        // 2. WebSocket 실시간 메시지 전송
        String destination = "/topic/chat/" + "room-" + message.getRoomId();
        webSocketSender.send(destination, message);


        log.info("서비스에서 수신 메시지 처리: " + message);
    }
}


