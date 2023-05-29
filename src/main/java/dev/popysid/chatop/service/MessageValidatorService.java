package dev.popysid.chatop.service;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.error.exception.MessageException;

public interface MessageValidatorService {

    void validateMessageRequest(MessageRequest message) throws MessageException;
}
