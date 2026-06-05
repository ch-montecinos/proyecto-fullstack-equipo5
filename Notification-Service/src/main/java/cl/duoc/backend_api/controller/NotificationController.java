package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Notification;
import cl.duoc.backend_api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAll() {
        return notificationService.findAll();
    }

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.save(notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }
}