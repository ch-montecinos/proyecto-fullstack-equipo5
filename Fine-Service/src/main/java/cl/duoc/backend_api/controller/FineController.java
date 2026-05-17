package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineRepository repository;

    @GetMapping
    public List<Fine> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Fine create(@RequestBody Fine fine) {
        return repository.save(fine);
    }
}