package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepository repo;

    @GetMapping
    public List<Loan> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Loan crear(@RequestBody Loan l) {
        return repo.save(l);
    }
}