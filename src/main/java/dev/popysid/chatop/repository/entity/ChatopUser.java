package dev.popysid.chatop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "chatop_users")
@EqualsAndHashCode(callSuper = true)
public class ChatopUser extends AbstractEntity {

    @Column(name = "chatop_email", nullable = false, unique = true)
    private String email;

    @Column(name = "chatop_password", nullable = false)
    private String password;
}
