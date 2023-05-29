package dev.popysid.chatop.helpers;

import dev.popysid.chatop.repository.entity.ChatopRental;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatopRentalRowMapper implements RowMapper<ChatopRental> {

    @Override
    public ChatopRental mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatopRental rental = new ChatopRental();
        rental.setId(rs.getLong("chatop_id"));
        rental.setName(rs.getString("chatop_name"));
        rental.setSurface(rs.getInt("chatop_surface"));
        rental.setPrice(rs.getInt("chatop_price"));
        rental.setPicture(rs.getString("chatop_picture"));
        rental.setDescription(rs.getString("chatop_description"));
        rental.setOwnerId(rs.getLong("owner_id"));
        rental.setCreated_at(rs.getDate("created_at"));
        rental.setUpdated_at(rs.getDate("updated_at"));
        return rental;
    }
}
