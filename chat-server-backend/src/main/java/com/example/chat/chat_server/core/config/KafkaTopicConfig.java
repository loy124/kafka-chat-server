package com.example.chat.chat_server.core.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic chatRoomTopic(){
        return TopicBuilder
                .name("chat-room")
                //메시지가 물리적으로 저장되는 단위
                .partitions(1)
                // 복제본 갯수
                .replicas(1)
                .build();
    }
}
