package dev.popysid.chatop.controller;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.controller.dto.response.MessageResponse;
import dev.popysid.chatop.helpers.AbstractMockedTest;
import dev.popysid.chatop.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Testing MessageController: Given a user send a message")
class MessageControllerTest extends AbstractMockedTest {

    @Nested
    class When_the_MessageRequest_is_valid {

        @InjectMocks
        private MessageController controllerTested;

        @Mock
        private MessageService mockedService;

        @Test
        void then_it_should_respond_with_success_message_and_status_OK() {
            MessageRequest request = new MessageRequest();
            request.setMessage("Test message");
            request.setUserId(1L);
            request.setRentalId(1L);

            MessageResponse responseOk = new MessageResponse("Message send with success");
            try {

                when(this.mockedService.send(any(MessageRequest.class))).thenReturn(responseOk);

                ResponseEntity<MessageResponse> response = this.controllerTested.sendMessage(request);

                assertEquals(responseOk, response.getBody());
                assertEquals(HttpStatus.OK, response.getStatusCode());
            } catch (Exception e) {
                fail(e);
            }
        }

    }

}