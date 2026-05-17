package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Search;
import cl.duoc.backend_api.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchRepository repository;

    @GetMapping
    public List<Search> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Search create(@RequestBody Search search) {
        return repository.save(search);
    }
}