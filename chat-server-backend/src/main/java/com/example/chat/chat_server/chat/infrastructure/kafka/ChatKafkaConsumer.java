package com.example.chat.chat_server.chat.infrastructure.kafka;

import com.example.chat.chat_server.chat.dto.ChatMessage;
import com.example.chat.chat_server.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @KafkaListener(topics = "chat-room", groupId = "chat-group")
    public void userMessageListener(ConsumerRecord<String, String> record) {
        try {
            ChatMessage message = objectMapper.readValue(record.value(), ChatMessage.class);
            log.info("유저용 Kafka 수신 메시지: " + message);
            chatService.handleReceivedMessage(message);  // WebSocket 전송 등
        } catch (Exception e) {
            log.error("[chat-group] 처리 실패: " + e.getMessage());
        }
    }

    // 로그 저장용 Consumer (같은 토픽, 다른 groupId)
    @KafkaListener(topics = "chat-room", groupId = "log-group")
    public void logMessageListener(ConsumerRecord<String, String> record) {
        try {
            ChatMessage message = objectMapper.readValue(record.value(), ChatMessage.class);
            log.info("🗃[log-group] 채팅 로그 저장용 수신: " + message);

        } catch (Exception e) {
            log.error("[log-group] 처리 실패: " + e.getMessage());
        }
    }
}