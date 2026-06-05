package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Search;
import cl.duoc.backend_api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public List<Search> getAll() {
        return searchService.findAll();
    }

    @PostMapping
    public ResponseEntity<Search> create(@RequestBody Search search) {
        Search savedSearch = searchService.save(search);
        return new ResponseEntity<>(savedSearch, HttpStatus.CREATED);
    }
}