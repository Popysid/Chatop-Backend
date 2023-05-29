package dev.popysid.chatop.repository;

import dev.popysid.chatop.repository.entity.ChatopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatopUserJpaRepository extends JpaRepository<ChatopUser, Long> {

    Optional<ChatopUser> findByEmail(String email);
}
