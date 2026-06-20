package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Préstamos", description = "Operaciones para la gestión de préstamos, plazos de entrega y devoluciones")
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Operation(summary = "Listar todos los préstamos", description = "Retorna la lista completa de préstamos y folios activos en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de préstamos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Loan>> listar() {
        List<Loan> prestamos = loanService.obtenerTodos();
        return ResponseEntity.ok(prestamos);
    }

    @Operation(summary = "Obtener préstamo por ID", description = "Busca un préstamo específico mediante su ID de transacción único.")
    @ApiResponse(responseCode = "200", description = "Préstamo localizado")
    @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Loan> obtenerPorId(@PathVariable Long id) {
        return loanService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo préstamo", description = "Crea una solicitud de préstamo, validando de forma interna el stock y las multas vía WebClient.")
    @ApiResponse(responseCode = "201", description = "Préstamo autorizado y registrado exitosamente")
    @PostMapping
    public ResponseEntity<Loan> crear(@RequestBody Loan l) {
        Loan prestamoCreado = loanService.registrarPrestamo(l);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoCreado);
    }

    @Operation(summary = "Actualizar un préstamo", description = "Modifica los datos de un préstamo o actualiza las fechas de entrega en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Préstamo no encontrado para modificar")
    @PutMapping("/{id}")
    public ResponseEntity<Loan> actualizar(@PathVariable Long id, @RequestBody Loan l) {
        try {
            Loan prestamoActualizado = loanService.actualizarPrestamo(id, l);
            return ResponseEntity.ok(prestamoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar o revocar un préstamo", description = "Remueve físicamente el registro del préstamo del sistema utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = loanService.eliminarPrestamo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}