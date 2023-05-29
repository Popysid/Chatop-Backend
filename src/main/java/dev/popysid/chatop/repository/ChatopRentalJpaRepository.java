package dev.popysid.chatop.repository;

import dev.popysid.chatop.repository.entity.ChatopRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatopRentalJpaRepository extends JpaRepository<ChatopRental, Long> {

}
