package dev.popysid.chatop.repository;

import dev.popysid.chatop.repository.entity.ChatopMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatopMessageJpaRepository extends JpaRepository<ChatopMessage, Long> {

}
