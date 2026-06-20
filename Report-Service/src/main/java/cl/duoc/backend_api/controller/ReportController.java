package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Report;
import cl.duoc.backend_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Reportes", description = "Operaciones para la generación, consulta y gestión de informes del sistema")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "Listar todos los reportes", description = "Retorna el historial completo de informes generados en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Listado de reportes obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok(reportService.findAll());
    }

    @Operation(summary = "Obtener un reporte por ID", description = "Busca un informe estructurado específico utilizando su ID único.")
    @ApiResponse(responseCode = "200", description = "Reporte encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Report> getById(@PathVariable Long id) {
        return reportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Generar un nuevo reporte", description = "Registra un nuevo documento informando su nombre, formato (PDF/EXCEL) y contenido crudo.")
    @ApiResponse(responseCode = "201", description = "Reporte creado con éxito")
    @PostMapping
    public ResponseEntity<Report> create(@RequestBody Report report) {
        Report savedReport = reportService.save(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un reporte", description = "Modifica las propiedades o actualiza los metadatos de un reporte basándose en su ID.")
    @ApiResponse(responseCode = "200", description = "Reporte actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Reporte no encontrado para modificar")
    @PutMapping("/{id}")
    public ResponseEntity<Report> update(@PathVariable Long id, @RequestBody Report report) {
        try {
            Report updatedReport = reportService.update(id, report);
            return ResponseEntity.ok(updatedReport);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un reporte", description = "Borra físicamente el registro del informe del sistema usando su ID.")
    @ApiResponse(responseCode = "204", description = "Reporte eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = reportService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}