package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Search;
import cl.duoc.backend_api.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    // 1. Listar todos
    public List<Search> findAll() {
        return searchRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Search> findById(Long id) {
        return searchRepository.findById(id);
    }

    // 3. Guardar / Crear
    public Search save(Search search) {
        return searchRepository.save(search);
    }

    // 4. Actualizar 
    public Search update(Long id, Search nuevoSearch) {
        return searchRepository.findById(id).map(busquedaExistente -> {
            busquedaExistente.setConsulta(nuevoSearch.getConsulta());
            busquedaExistente.setFiltro(nuevoSearch.getFiltro());
            busquedaExistente.setFechaBusqueda(nuevoSearch.getFechaBusqueda());
            return searchRepository.save(busquedaExistente);
        }).orElseThrow(() -> new RuntimeException("Registro de búsqueda no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean delete(Long id) {
        if (searchRepository.existsById(id)) {
            searchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}