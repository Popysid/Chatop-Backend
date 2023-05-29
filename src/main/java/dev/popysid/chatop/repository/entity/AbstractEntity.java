package dev.popysid.chatop.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatop_id", nullable = false)
    private Long id;

    @Column(name = "chatop_name", nullable = false)
    private String name;

    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;
}
