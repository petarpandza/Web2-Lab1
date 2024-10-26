package hr.fer.web2.lab1.ticket;

import java.util.UUID;

public class TicketResponse {
    private String qrCodeImageUrl;
    private UUID ticketId;

    public TicketResponse(String qrCodeImageUrl, UUID ticketId) {
        this.qrCodeImageUrl = qrCodeImageUrl;
        this.ticketId = ticketId;
    }

    public String getQrCodeImageUrl() {
        return qrCodeImageUrl;
    }

    public void setQrCodeImageUrl(String qrCodeImageUrl) {
        this.qrCodeImageUrl = qrCodeImageUrl;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }
}
