package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Return;
import cl.duoc.backend_api.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping
    public List<Return> getAll() {
        return returnService.findAll();
    }

    @PostMapping
    public ResponseEntity<Return> create(@RequestBody Return returnEntity) {
        Return savedReturn = returnService.save(returnEntity);
        return new ResponseEntity<>(savedReturn, HttpStatus.CREATED);
    }
}