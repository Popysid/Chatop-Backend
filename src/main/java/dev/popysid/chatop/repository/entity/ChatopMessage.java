package dev.popysid.chatop.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "chatop_messages")
public class ChatopMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatop_id", nullable = false)
    private Long id;

    @Column(name = "chatop_message", nullable = false)
    private String message;

    @Column(name = "chatop_user_id", nullable = false)
    private Long userId;

    @Column(name = "chatop_rental_id", nullable = false)
    private Long rentalId;

}
