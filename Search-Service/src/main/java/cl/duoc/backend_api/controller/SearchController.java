package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Search;
import cl.duoc.backend_api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Búsquedas", description = "Operaciones para auditoría, registro e historial de consultas realizadas por los usuarios")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Operation(summary = "Listar todo el historial de búsquedas", description = "Retorna la lista completa de consultas almacenadas en los logs de auditoría.")
    @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<Search>> getAll() {
        return ResponseEntity.ok(searchService.findAll());
    }

    @Operation(summary = "Obtener un registro de búsqueda por ID", description = "Busca un log de consulta específico mediante su ID único.")
    @ApiResponse(responseCode = "200", description = "Registro localizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Search> getById(@PathVariable Long id) {
        return searchService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un log de búsqueda", description = "Guarda la traza de lo que un usuario buscó, el filtro utilizado (AUTOR, TÍTULO) y la fecha.")
    @ApiResponse(responseCode = "201", description = "Log de búsqueda registrado exitosamente")
    @PostMapping
    public ResponseEntity<Search> create(@RequestBody Search search) {
        Search savedSearch = searchService.save(search);
        return new ResponseEntity<>(savedSearch, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un log de búsqueda", description = "Modifica los metadatos de un registro de consulta en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Registro actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado para modificar")
    @PutMapping("/{id}")
    public ResponseEntity<Search> update(@PathVariable Long id, @RequestBody Search search) {
        try {
            Search updatedSearch = searchService.update(id, search);
            return ResponseEntity.ok(updatedSearch);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un registro de búsqueda", description = "Remueve físicamente la traza de consulta del sistema usando su ID.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado de forma exitosa")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = searchService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}