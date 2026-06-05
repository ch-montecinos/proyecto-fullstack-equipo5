package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Report;
import cl.duoc.backend_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAll() {
        return reportService.findAll();
    }

    @PostMapping
    public ResponseEntity<Report> create(@RequestBody Report report) {
        Report savedReport = reportService.save(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }
}