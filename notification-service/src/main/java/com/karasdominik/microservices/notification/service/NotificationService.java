package com.karasdominik.microservices.notification.service;

import com.karasdominik.microservices.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent event) {
        log.info("Received order placed event: {}", event);
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("springshop@email.com");
            message.setTo(event.getEmail().toString());
            message.setSubject("Order Confirmation");
            message.setText("Your order has been placed successfully. Order number: " + event.getOrderNumber());
        };
        try {
            mailSender.send(preparator);
            log.info("Order confirmation email sent successfully");
        } catch (Exception e) {
            log.error("Failed to send order confirmation email", e);
            throw new RuntimeException("Failed to send order confirmation email");
        }
    }
}
