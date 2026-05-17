package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository repo;

    @GetMapping
    public List<Book> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Book crear(@RequestBody Book b) {
        return repo.save(b);
    }
}