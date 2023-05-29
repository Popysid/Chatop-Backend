package dev.popysid.chatop.impl;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.controller.dto.response.MessageResponse;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.helpers.AbstractMockedTest;
import dev.popysid.chatop.repository.ChatopMessageJpaRepository;
import dev.popysid.chatop.repository.entity.ChatopMessage;
import dev.popysid.chatop.service.MessageValidatorService;
import dev.popysid.chatop.service.impl.MessageServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testing MessageService: Given a user send a message")
class MessageServiceImplTest extends AbstractMockedTest {

    @Nested
    class When_the_MessageRequest_is_valid {

        @InjectMocks
        private MessageServiceImpl serviceTested;

        @Mock
        private MessageValidatorService mockedService;

        @Mock
        private ChatopMessageJpaRepository mockedRepository;

        @Test
        void then_it_should_return_success_message() {
            MessageRequest requestTested = new MessageRequest();
            requestTested.setMessage("Test message");
            requestTested.setUserId(1L);
            requestTested.setRentalId(1L);

            ChatopMessage mockedMessage = new ChatopMessage();
            mockedMessage.setMessage("Test message");
            mockedMessage.setUserId(1L);
            mockedMessage.setRentalId(1L);

            ChatopMessage savedMessage = new ChatopMessage();
            savedMessage.setId(1L);
            savedMessage.setMessage("Test message");
            savedMessage.setUserId(1L);
            savedMessage.setRentalId(1L);

            MessageResponse responseSuccess = new MessageResponse("Message send with success");
            try {

                doNothing().when(this.mockedService).validateMessageRequest(requestTested);

                when(this.mockedRepository.saveAndFlush(mockedMessage)).thenReturn(savedMessage);

                MessageResponse response = this.serviceTested.send(requestTested);

                assertEquals(responseSuccess, response);
            } catch (MessageException e) {
                fail(e);
            }
        }
    }

    @Nested
    class When_the_MessageRequest_is_invalid {

        @InjectMocks
        private MessageServiceImpl serviceTested;

        @Mock
        private MessageValidatorService mockedService;


        @Test
        @SneakyThrows
        void then_it_should_throw_MessageException_with_invalid_user_id() {
            MessageRequest requestTested = new MessageRequest();
            requestTested.setMessage("Test message");
            requestTested.setUserId(3L);
            requestTested.setRentalId(1L);

            doThrow(new MessageException("Message invalid! User with id:3 not found."))
                    .when(this.mockedService).validateMessageRequest(requestTested);

            MessageException thrown = assertThrows(MessageException.class,
                    ()-> this.serviceTested.send(requestTested));

            assertTrue(thrown.getMessage().contains("Message invalid! "));
            assertTrue(thrown.getMessage().contains("User with id:3 "));
            assertFalse(thrown.getMessage().contains("Rental with id:1 "));
            assertTrue(thrown.getMessage().contains("not found."));
        }

        @Test
        @SneakyThrows
        void then_it_should_throw_MessageException_with_invalid_rental_id() {
            MessageRequest requestTested = new MessageRequest();
            requestTested.setMessage("Test message");
            requestTested.setUserId(1L);
            requestTested.setRentalId(13L);

            doThrow(new MessageException("Message invalid! Rental with id:13 not found."))
                    .when(this.mockedService).validateMessageRequest(requestTested);

            MessageException thrown = assertThrows(MessageException.class,
                    ()-> this.serviceTested.send(requestTested));

            assertTrue(thrown.getMessage().contains("Message invalid! "));
            assertFalse(thrown.getMessage().contains("User with id:1 "));
            assertTrue(thrown.getMessage().contains("Rental with id:13 "));
            assertTrue(thrown.getMessage().contains("not found."));
        }
    }
}