package hr.fer.web2.lab1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Init {

    public static void run() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading sqlite driver.");
            throw new RuntimeException(e);
        }

        String url = "jdbc:sqlite:db.sqlite";

        String dropTable =
                """
                DROP TABLE IF EXISTS tickets;
                """;
        String createTable =
                """
                CREATE TABLE tickets (
                    id TEXT PRIMARY KEY,
                    vatin TEXT NOT NULL,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    creation_date TEXT NOT NULL
                );
                """;
        String insertQuery =
                """
                    INSERT INTO tickets (id, vatin, first_name, last_name, creation_date) VALUES
                    ('479944d3-314b-4a7f-bac9-6ab89f1af882', '12345678901', 'Petar', 'Petrić', '2021-10-10 10:00:00'),
                    ('3f5ad88f-b10d-4505-853c-13a8f5553ca0', '23456789012', 'Marko', 'Markić', '2021-09-10 10:00:00'),
                    ('53b4802f-9c89-4b7d-8dbc-c181749aa9cb', '34567890123', 'Ivan', 'Ivanić', '2021-08-10 10:00:00'),
                    ('a5e76c12-e013-4e4d-9a40-5b05f438e8b4', '45678901234', 'Marija', 'Marić', '2021-07-10 10:00:00'),
                    ('9620e6b6-cc0e-4864-8f1e-b3e5989763c0', '56789012345', 'Lara', 'Larić', '2021-06-10 10:00:00');
                """;


        try (Connection con = DriverManager.getConnection(url);
             Statement stmt = con.createStatement()) {
            stmt.execute(dropTable);
            System.out.println("Dropped table tickets");
            stmt.execute(createTable);
            System.out.println("Created table tickets");
            stmt.execute(insertQuery);
            System.out.println("Inserted data into table tickets");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
