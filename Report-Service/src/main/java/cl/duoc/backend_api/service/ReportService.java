package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Report;
import cl.duoc.backend_api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // 1. Listar todos
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    // 3. Guardar / Crear
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    // 4. Actualizar 
    public Report update(Long id, Report nuevoReporte) {
        return reportRepository.findById(id).map(reporteExistente -> {
            reporteExistente.setNombre(nuevoReporte.getNombre());
            reporteExistente.setTipo(nuevoReporte.getTipo());
            reporteExistente.setContenido(nuevoReporte.getContenido());
            return reportRepository.save(reporteExistente);
        }).orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean delete(Long id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}