package hr.fer.web2.lab1;

import hr.fer.web2.lab1.ticket.TicketRepository;
import hr.fer.web2.lab1.ticket.TicketRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TicketController {

    @PostMapping("/createTicket")
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest, HttpServletRequest request) {
        TicketRepository ticketRepository = new TicketRepository();
        if (ticketRepository.getNumberOfTicketsByVatin(ticketRequest.getVatin()) >= 3) {
            return ResponseEntity.badRequest().body("Only 3 tickets allowed per OIB.");
        }
        var ticketId = ticketRepository.addTicket(ticketRequest.getVatin(), ticketRequest.getFirstName(), ticketRequest.getLastName());
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String ticketUrl = baseUrl + "/ticket/" + ticketId;

        try {
            byte[] qrCodeImage = QRCodeUtil.generateQRCodeImage(ticketUrl);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR code.");
        }
    }

}