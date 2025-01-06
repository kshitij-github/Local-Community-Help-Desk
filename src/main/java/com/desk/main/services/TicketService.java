package com.desk.main.services;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desk.main.entity.Notification;
import com.desk.main.entity.Ticket;
import com.desk.main.repository.NotificationRepository;
import com.desk.main.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        Notification notification = new Notification();
        notification.setTicketId(savedTicket.getId());
        notification.setMessage("Ticket created: " + ticket.getDescription());
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
        return savedTicket;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicketStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(status);
        ticketRepository.save(ticket);

        Notification notification = new Notification();
        notification.setTicketId(id);
        notification.setMessage("Ticket status updated to: " + status);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return ticket;
    }
}
