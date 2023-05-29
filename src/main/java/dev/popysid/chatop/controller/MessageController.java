package dev.popysid.chatop.controller;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.controller.dto.response.MessageResponse;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest message) throws MessageException {

        MessageResponse response = this.service.send(message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
