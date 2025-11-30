package com.example.homework5.kafka;

import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    public void sendMessage(String topic, String message) {
        // Просто выводим сообщение в консоль, без Kafka
        System.out.println("KafkaProducer (mock): сообщение для топика " + topic + ": " + message);
    }
}
