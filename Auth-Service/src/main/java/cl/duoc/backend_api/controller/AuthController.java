package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Auth;
import cl.duoc.backend_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController { 

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Auth> listar() { 
        return authService.findAll(); 
    }

    @PostMapping
    public ResponseEntity<Auth> registrar(@RequestBody Auth a) { 
        Auth savedAuth = authService.save(a);
        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }
}