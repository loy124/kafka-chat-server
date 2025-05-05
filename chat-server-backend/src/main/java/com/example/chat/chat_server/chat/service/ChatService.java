package com.example.chat.chat_server.chat.service;

import com.example.chat.chat_server.chat.dto.ChatMessage;
import com.example.chat.chat_server.chat.infrastructure.kafka.ChatKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatKafkaProducer chatKafkaProducer;

    public void sendMessage(ChatMessage chatMessage) {
        chatKafkaProducer.send(chatMessage);
    }

    public void handleReceivedMessage(ChatMessage message) {
        // 여기서 DB 저장, WebSocket 전송 등의 후속 작업 처리
        System.out.println("서비스에서 수신 메시지 처리: " + message);
    }
}


