package dev.popysid.chatop.service.impl;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.repository.ChatopRentalJpaRepository;
import dev.popysid.chatop.repository.ChatopUserJpaRepository;
import dev.popysid.chatop.repository.entity.ChatopRental;
import dev.popysid.chatop.repository.entity.ChatopUser;
import dev.popysid.chatop.service.MessageValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageValidatorServiceImpl implements MessageValidatorService {

    private final ChatopUserJpaRepository userRepository;
    private final ChatopRentalJpaRepository rentalRepository;

    @Autowired
    public MessageValidatorServiceImpl(ChatopUserJpaRepository userRepository, ChatopRentalJpaRepository rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void validateMessageRequest(MessageRequest message) throws MessageException {
        boolean error = false;
        StringBuilder errorMessage = new StringBuilder("Message invalid! ");
        Optional<ChatopUser> optionalChatopUser = this.userRepository.findById(message.getUserId());
        if (optionalChatopUser.isEmpty()) {
            error = true;
            errorMessage.append("User with id:").append(message.getUserId()).append(" ");
        }
        Optional<ChatopRental> optionalChatopRental = this.rentalRepository.findById(message.getRentalId());
        if (optionalChatopRental.isEmpty()) {
            error = true;
            errorMessage.append("Rental with id:").append(message.getRentalId()).append(" ");
        }
        if(error) {
            errorMessage.append("not found.");
            throw new MessageException(errorMessage.toString());
        }
    }
}
