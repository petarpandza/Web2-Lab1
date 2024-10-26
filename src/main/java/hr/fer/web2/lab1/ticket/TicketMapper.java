package hr.fer.web2.lab1.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TicketMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static TicketModel map(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String vatin = rs.getString("vatin");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        LocalDate creationDate = LocalDate.parse(rs.getString("creation_date"), formatter);
        return new TicketModel(id, vatin, firstName, lastName, creationDate);
    }
}
