package com.example.homework5.userservice;

import com.example.homework5.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KafkaProducer kafkaProducer;

    public UserService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void createUser(String email) {
        // Тут можно добавить сохранение в репозиторий, если нужно
        kafkaProducer.sendMessage("user-topic", "CREATE:" + email);
        System.out.println("CREATE-событие отправлено в Kafka для " + email);
    }

    public void deleteUser(String email) {
        // Тут можно добавить удаление из репозитория, если нужно
        kafkaProducer.sendMessage("user-topic", "DELETE:" + email);
        System.out.println("DELETE-событие отправлено в Kafka для " + email);
    }
}
