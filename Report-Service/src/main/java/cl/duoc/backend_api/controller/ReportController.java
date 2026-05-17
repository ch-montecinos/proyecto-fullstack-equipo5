package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Report;
import cl.duoc.backend_api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository repository;

    @GetMapping
    public List<Report> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Report create(@RequestBody Report report) {
        return repository.save(report);
    }
}