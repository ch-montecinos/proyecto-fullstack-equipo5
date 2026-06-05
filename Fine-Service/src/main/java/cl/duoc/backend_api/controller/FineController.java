package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.service.FineService; // IMPORTAMOS EL NUEVO SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService; // CAMBIADO: Conectamos al Service, removiendo el Repo

    @GetMapping
    public ResponseEntity<List<Fine>> getAll() {
        // Solicitamos los datos al Service y devolvemos un 200 OK estructurado
        List<Fine> multas = fineService.obtenerTodas();
        return ResponseEntity.ok(multas);
    }

    @PostMapping
    public ResponseEntity<Fine> create(@RequestBody Fine fine) {
        // Delegamos el proceso a la capa Service
        Fine nuevaMultas = fineService.guardarFine(fine);
        
        // Devolvemos el estatus 201 Created correspondiente
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMultas);
    }
}