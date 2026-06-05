package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.service.LoanService; // IMPORTAMOS EL NUEVO SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService; // CAMBIADO: Conectamos al Service

    @GetMapping
    public ResponseEntity<List<Loan>> listar() {
        List<Loan> prestamos = loanService.obtenerTodos();
        return ResponseEntity.ok(prestamos);
    }

    @PostMapping
    public ResponseEntity<Loan> crear(@RequestBody Loan l) {
        // El controlador recibe la petición y delega la orquestación al Service
        Loan prestamoCreado = loanService.registrarPrestamo(l);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoCreado);
    }
}