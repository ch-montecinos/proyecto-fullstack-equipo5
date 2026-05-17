package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Return;
import cl.duoc.backend_api.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnRepository repository;

    @GetMapping
    public List<Return> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Return create(@RequestBody Return returnEntity) {
        return repository.save(returnEntity);
    }
}