package dev.popysid.chatop;

import dev.popysid.chatop.helpers.AbstractH2InitializedTest;
import dev.popysid.chatop.repository.entity.ChatopRental;
import dev.popysid.chatop.repository.entity.ChatopUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Testing ChatopApplication")
class ChatopApplicationTests extends AbstractH2InitializedTest {

	@Test
	void application_context_should_load() {
	}

	@Test
	void embedded_database_initialized() {
		ChatopUser userOne = getChatopUserById(1L);
		ChatopUser userTwo = getChatopUserById(2L);

		assertNotNull(userOne);
		assertNotNull(userTwo);

		ChatopRental rentalOne = getChatopRentalById(1L);
		ChatopRental rentalTwo = getChatopRentalById(2L);

		assertNotNull(rentalOne);
		assertNotNull(rentalTwo);
	}
}

