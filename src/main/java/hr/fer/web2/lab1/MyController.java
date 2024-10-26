package hr.fer.web2.lab1;

import hr.fer.web2.lab1.ticket.TicketModel;
import hr.fer.web2.lab1.ticket.TicketRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class MyController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("numTickets", new TicketRepository().getTicketCount());
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/ticket/{ticketId}")
    public String ticket(@PathVariable String ticketId, Model model, @AuthenticationPrincipal OAuth2User user) {
        TicketModel ticket = new TicketRepository().getTicketById(UUID.fromString(ticketId));
        model.addAttribute("ticket", ticket);
        model.addAttribute("email", user.getAttribute("email"));
        return "ticket";
    }

}
