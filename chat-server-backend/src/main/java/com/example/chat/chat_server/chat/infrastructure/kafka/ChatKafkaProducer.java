package com.example.chat.chat_server.chat.infrastructure.kafka;

import com.example.chat.chat_server.chat.dto.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // Kafka에 JSON 메시지 전송
    public void send(ChatMessage chatMessage) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(chatMessage);  // 직렬화
            kafkaTemplate.send("chat-room", chatMessage.getRoomId(), jsonMessage);  // 토픽 전송
            System.out.println("✅ Kafka 전송 완료 → " + jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Kafka 메시지 직렬화 실패", e);
        }
    }
}