package hr.fer.web2.lab1.ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TicketRepository {
    
    private final Connection connection;
    private static final String URL = "jdbc:sqlite:db.sqlite";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public TicketRepository() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public TicketModel getTicketById(UUID id) {
        try (var stmt = connection.prepareStatement("SELECT * FROM tickets WHERE id = ?")) {
            stmt.setString(1, id.toString());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return TicketMapper.map(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public UUID addTicket(String vatin, String firstName, String lastName) {
        try (var stmt = connection
                .prepareStatement("INSERT INTO tickets (id, vatin, first_name, last_name, creation_date) VALUES (?, ?, ?, ?, ?)")) {
            var id = UUID.randomUUID();
            stmt.setString(1, id.toString());
            stmt.setString(2, vatin);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, formatter.format(LocalDateTime.now()));
            stmt.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int getTicketCount() {
        try (var stmt = connection.prepareStatement("SELECT COUNT(*) FROM tickets")) {
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    
    public int getNumberOfTicketsByVatin(String vatin) {
        try (var stmt = connection.prepareStatement("SELECT COUNT(*) FROM tickets WHERE vatin = ?")) {
            stmt.setString(1, vatin);
            var rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
