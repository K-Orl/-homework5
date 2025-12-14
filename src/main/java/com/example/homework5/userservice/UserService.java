package com.example.homework5.userservice;

import com.example.homework5.kafka.KafkaProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KafkaProducer kafkaProducer;

    public UserService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackCreateUser")
    public void createUser(String email) {
        kafkaProducer.sendMessage("user-topic", "CREATE:" + email);
        System.out.println("CREATE-событие отправлено в Kafka для " + email);
    }

    public void deleteUser(String email) {
        kafkaProducer.sendMessage("user-topic", "DELETE:" + email);
        System.out.println("DELETE-событие отправлено в Kafka для " + email);
    }

    public void fallbackCreateUser(String email, Throwable t) {
        System.out.println("Fallback: не удалось создать пользователя " + email);
    }
}
