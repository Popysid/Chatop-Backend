package dev.popysid.chatop.service.impl;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.controller.dto.response.MessageResponse;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.repository.ChatopMessageJpaRepository;
import dev.popysid.chatop.repository.entity.ChatopMessage;
import dev.popysid.chatop.service.MessageService;
import dev.popysid.chatop.service.MessageValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageValidatorService validatorService;
    private final ChatopMessageJpaRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageValidatorService validatorService,
                              ChatopMessageJpaRepository messageRepository) {
        this.validatorService = validatorService;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponse send(MessageRequest message) throws MessageException {

        this.validatorService.validateMessageRequest(message);
        ChatopMessage entity = mapRequestToEntity(message);
        ChatopMessage saved =  this.messageRepository.saveAndFlush(entity);
        String acknowledgment;
        if (saved.getId() != null) {
            acknowledgment = "Message send with success";
        } else {
            acknowledgment = "Message cannot be send";
        }
        return new MessageResponse(acknowledgment);
    }
    private ChatopMessage mapRequestToEntity(MessageRequest message) {
        ChatopMessage entity = new ChatopMessage();
        entity.setMessage(message.getMessage());
        entity.setUserId(message.getUserId());
        entity.setRentalId(message.getRentalId());
        return entity;
    }
}
