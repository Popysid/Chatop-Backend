package dev.popysid.chatop.impl;

import dev.popysid.chatop.controller.dto.request.MessageRequest;
import dev.popysid.chatop.error.exception.MessageException;
import dev.popysid.chatop.helpers.AbstractMockedTest;
import dev.popysid.chatop.repository.ChatopRentalJpaRepository;
import dev.popysid.chatop.repository.ChatopUserJpaRepository;
import dev.popysid.chatop.repository.entity.ChatopRental;
import dev.popysid.chatop.repository.entity.ChatopUser;
import dev.popysid.chatop.service.impl.MessageValidatorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Testing MessageValidatorService: Given a user send a message")
class MessageValidatorServiceImplTest extends AbstractMockedTest {

    @Nested
    class When_the_MessageRequest_is_valid {

        @InjectMocks
        private MessageValidatorServiceImpl serviceTested;

        @Mock
        private ChatopUserJpaRepository mockedUserRepository;

        @Mock
        private ChatopRentalJpaRepository mockedRentalRepository;

        @Test
        void then_it_should_do_nothing() {

            MessageRequest requestTested = new MessageRequest();
            requestTested.setMessage("Test message");
            requestTested.setUserId(1L);
            requestTested.setRentalId(1L);

            ChatopUser chatopUser = new ChatopUser();
            chatopUser.setId(1L);

            ChatopRental chatopRental = new ChatopRental();
            chatopRental.setId(1L);

            when(this.mockedUserRepository.findById(requestTested.getUserId())).thenReturn(Optional.of(chatopUser));

            when(this.mockedRentalRepository.findById(requestTested.getRentalId())).thenReturn(Optional.of(chatopRental));

            try {
                this.serviceTested.validateMessageRequest(requestTested);
            } catch (MessageException e) {
                fail(e);
            }
        }
    }

    @Nested
    class When_the_MessageRequest_is_invalid {

        @InjectMocks
        private MessageValidatorServiceImpl serviceTested;

        @Mock
        private ChatopUserJpaRepository mockedUserRepository;

        @Mock
        private ChatopRentalJpaRepository mockedRentalRepository;

        @Test
        void then_it_should_throw_MessageException() {

            MessageRequest requestTested = new MessageRequest();
            requestTested.setMessage("Test message");
            requestTested.setUserId(13L);
            requestTested.setRentalId(13L);

            when(this.mockedUserRepository.findById(requestTested.getUserId())).thenReturn(Optional.empty());

            when(this.mockedRentalRepository.findById(requestTested.getRentalId())).thenReturn(Optional.empty());

            MessageException thrown = assertThrows(MessageException.class, ()-> this.serviceTested.validateMessageRequest(requestTested));

            assertEquals(
                    "Message invalid! User with id:13 Rental with id:13 not found.",
                    thrown.getMessage()
            );
        }
    }
}