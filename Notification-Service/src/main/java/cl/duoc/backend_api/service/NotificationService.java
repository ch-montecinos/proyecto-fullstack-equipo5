package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Notification;
import cl.duoc.backend_api.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // 1. Listar todas
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    // 3. Guardar / Crear
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    // 4. Actualizar 
    public Notification update(Long id, Notification nuevaNotificacion) {
        return notificationRepository.findById(id).map(notificacionExistente -> {
            notificacionExistente.setIdUsuario(nuevaNotificacion.getIdUsuario());
            notificacionExistente.setMensaje(nuevaNotificacion.getMensaje());
            notificacionExistente.setTipo(nuevaNotificacion.getTipo());
            return notificationRepository.save(notificacionExistente);
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));
    }

    // 5. Eliminar
    public boolean delete(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}