package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Auth;
import cl.duoc.backend_api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController { 
    @Autowired
    private AuthRepository repo;

    @GetMapping
    public List<Auth> listar() { return repo.findAll(); }

    @PostMapping
    public Auth registrar(@RequestBody Auth a) { return repo.save(a); }
}