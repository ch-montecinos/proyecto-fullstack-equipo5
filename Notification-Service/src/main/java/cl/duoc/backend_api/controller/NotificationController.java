package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Notification;
import cl.duoc.backend_api.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping
    public List<Notification> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return repository.save(notification);
    }
}