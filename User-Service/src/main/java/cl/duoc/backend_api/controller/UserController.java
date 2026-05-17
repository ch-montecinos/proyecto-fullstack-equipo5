package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> listar() { return repo.findAll(); }

    @PostMapping
    public User crear(@RequestBody User u) { return repo.save(u); }
}
