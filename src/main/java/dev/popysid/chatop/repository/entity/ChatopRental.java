package dev.popysid.chatop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "chatop_rentals")
@EqualsAndHashCode(callSuper = true)
public class ChatopRental extends AbstractEntity {

    @Column(name = "chatop_surface", nullable = false)
    private Integer surface;

    @Column(name = "chatop_price", nullable = false)
    private Integer price;

    @Column(name = "chatop_picture", nullable = false)
    private String picture;

    @Column(name = "chatop_description", nullable = false)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
}
