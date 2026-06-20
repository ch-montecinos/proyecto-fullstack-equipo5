package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.repository.FineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FineServiceTest {

    @Mock
    private FineRepository repository;

    @InjectMocks
    private FineService fineService;

    @Test
    public void probarObtenerTodas() {
        List<Fine> listaMock = new ArrayList<>();
        listaMock.add(new Fine());
        when(repository.findAll()).thenReturn(listaMock);

        assertFalse(fineService.obtenerTodas().isEmpty());
    }

    @Test
    public void probarObtenerPorId() {
        Fine fine = new Fine();
        fine.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(fine));

        assertTrue(fineService.obtenerPorId(1L).isPresent());
    }

    @Test
    public void probarGuardarFine() {
        Fine fine = new Fine();
        when(repository.save(fine)).thenReturn(fine);

        assertNotNull(fineService.guardarFine(fine));
    }

    @Test
    public void probarEliminarFine() {
        when(repository.existsById(1L)).thenReturn(true);
        
        assertTrue(fineService.eliminarFine(1L));
    }
}