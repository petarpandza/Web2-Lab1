package hr.fer.web2.lab1.ticket;

import java.time.LocalDate;
import java.util.UUID;

public class TicketModel {
    private UUID id;
    private String vatin;
    private String firstName;
    private String lastName;
    private LocalDate creationDate;

    public TicketModel(UUID id, String vatin, String firstName, String lastName, LocalDate creationDate) {
        this.id = id;
        this.vatin = vatin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVatin() {
        return vatin;
    }

    public void setVatin(String vatin) {
        this.vatin = vatin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "TicketModel{" +
                "id=" + id +
                ", vatin='" + vatin + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
