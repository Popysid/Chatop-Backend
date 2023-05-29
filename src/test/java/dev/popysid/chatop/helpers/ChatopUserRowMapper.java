package dev.popysid.chatop.helpers;

import dev.popysid.chatop.repository.entity.ChatopUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatopUserRowMapper implements RowMapper<ChatopUser> {

    @Override
    public ChatopUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatopUser user = new ChatopUser();
        user.setId(rs.getLong("chatop_id"));
        user.setName(rs.getString("chatop_name"));
        user.setEmail(rs.getString("chatop_email"));
        user.setPassword(rs.getString("chatop_password"));
        user.setCreated_at(rs.getDate("created_at"));
        user.setUpdated_at(rs.getDate("updated_at"));
        return user;
    }
}
