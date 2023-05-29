package dev.popysid.chatop.service;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.controller.dto.response.MessageResponse;
import dev.popysid.chatop.error.exception.MessageException;

public interface MessageService {

    MessageResponse send(MessageRequest message) throws MessageException;
}
