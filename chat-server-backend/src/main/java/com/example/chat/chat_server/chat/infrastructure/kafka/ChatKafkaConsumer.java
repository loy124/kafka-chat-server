package com.example.chat.chat_server.chat.infrastructure.kafka;

import com.example.chat.chat_server.chat.dto.ChatMessage;
import com.example.chat.chat_server.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @KafkaListener(topics = "chat-room", groupId = "chat-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            String json = record.value(); // Kafka 메시지 값
            ChatMessage message = objectMapper.readValue(json, ChatMessage.class);

            System.out.println("Kafka 수신 메시지: " + message);

            // 서비스로 위임
            chatService.handleReceivedMessage(message);

        } catch (Exception e) {
            System.err.println("Kafka 메시지 처리 실패: " + e.getMessage());
        }
    }
}