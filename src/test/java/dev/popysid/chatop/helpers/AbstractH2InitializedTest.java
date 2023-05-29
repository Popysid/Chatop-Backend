package dev.popysid.chatop.helpers;

import dev.popysid.chatop.repository.entity.ChatopRental;
import dev.popysid.chatop.repository.entity.ChatopUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles({"test"})
@SpringBootTest
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class AbstractH2InitializedTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractH2InitializedTest.class);

    private static final String DROP = "DROP ALL OBJECTS;";

    private static final String CREATE_USERS = "CREATE TABLE " +
            "`chatop_users` ( " +
            "`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
            "`chatop_name` VARCHAR(150) NOT NULL, " +
            "`chatop_email` VARCHAR(150) NOT NULL UNIQUE, " +
            "`chatop_password` VARCHAR(50) NOT NULL, " +
            "`created_at` DATE, " +
            "`updated_at` DATE " +
            ");";

    private static final String CREATE_RENTALS = "CREATE TABLE " +
            "`chatop_rentals` ( " +
            "`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
            "`chatop_name` VARCHAR(150) NOT NULL, " +
            "`chatop_surface` INTEGER NOT NULL, " +
            "`chatop_price` INTEGER NOT NULL, " +
            "`chatop_picture` TINYTEXT NOT NULL, " +
            "`chatop_description` TINYTEXT NOT NULL, " +
            "`owner_id` INTEGER NOT NULL, " +
            "`created_at` DATE, " +
            "`updated_at` DATE " +
            ");";

    private static final String CREATE_MESSAGES = "CREATE TABLE " +
            "`chatop_messages` ( " +
            "`chatop_id` INTEGER PRIMARY KEY AUTO_INCREMENT, " +
            "`chatop_message` TINYTEXT NOT NULL, " +
            "`chatop_user_id` INTEGER NOT NULL, " +
            "`chatop_rental_id` INTEGER NOT NULL" +
            ");";

    private static final String INSERT_USERS = "INSERT INTO chatop_users " +
            "(chatop_id, chatop_name, chatop_email, chatop_password, created_at, updated_at) " +
            "VALUES " +
            "(1, 'Test User 1', 'test1@test.com', 'Test@1234', CURRENT_DATE, CURRENT_DATE), " +
            "(2, 'Test User 2', 'test2@test.com', 'Test1234!', CURRENT_DATE, CURRENT_DATE);";

    private static final String INSERT_RENTALS = "INSERT INTO chatop_rentals " +
            "(chatop_id, chatop_name, chatop_surface, chatop_price, chatop_picture, chatop_description, owner_id, created_at, updated_at) " +
            "VALUES " +
            "(1, 'Test House 1', 50, 25, '[\"http://picture1.jpeg\"]', 'Test description 1', 1, CURRENT_DATE, CURRENT_DATE), " +
            "(2, 'Test House 2', 300, 150, '[\"http://picture2.jpeg\"]', 'Test description 2', 2, CURRENT_DATE, CURRENT_DATE);";

    private static final String SELECT_ALL_USERS = "SELECT * FROM chatop_users;";

    private static final String SELECT_ALL_RENTALS = "SELECT * FROM chatop_rentals;";

    private static final String SELECT_USER_BY_ID = "SELECT * FROM chatop_users WHERE chatop_id = ?;";

    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM chatop_users WHERE chatop_email = ?;";

    private static final String SELECT_RENTAL_BY_RENTAL_ID = "SELECT * FROM chatop_rentals WHERE chatop_id = ?;";

    private static final String SELECT_RENTAL_BY_OWNER_ID = "SELECT * FROM chatop_rentals WHERE owner_id = ?;";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void initDb() {
        this.jdbcTemplate.execute(DROP);
        this.jdbcTemplate.execute(CREATE_USERS);
        this.jdbcTemplate.execute(INSERT_USERS);
        List<ChatopUser> chatopUsers = this.jdbcTemplate.query(SELECT_ALL_USERS, new ChatopUserRowMapper());
        LOGGER.info("Inserted {} Chatop Users in database", chatopUsers.size());
        this.jdbcTemplate.execute(CREATE_RENTALS);
        this.jdbcTemplate.execute(INSERT_RENTALS);
        List<ChatopRental> chatopRentals = this.jdbcTemplate.query(SELECT_ALL_RENTALS, new ChatopRentalRowMapper());
        LOGGER.info("Inserted {} Chatop Rentals in database.", chatopRentals.size());
        this.jdbcTemplate.execute(CREATE_MESSAGES);
    }

    protected ChatopUser getChatopUserById(Long id) {
        return this.jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new ChatopUserRowMapper(), id);
    }

    protected ChatopUser getChatopUserByEmail(String email) {
        return this.jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL, new ChatopUserRowMapper(), email);
    }

    protected ChatopRental getChatopRentalById(Long id) {
        return this.jdbcTemplate.queryForObject(SELECT_RENTAL_BY_RENTAL_ID, new ChatopRentalRowMapper(), id);
    }

    protected ChatopRental getChatopRentalByOwnerId(Long ownerId) {
        return this.jdbcTemplate.queryForObject(SELECT_RENTAL_BY_OWNER_ID, new ChatopRentalRowMapper(), ownerId);
    }
}
