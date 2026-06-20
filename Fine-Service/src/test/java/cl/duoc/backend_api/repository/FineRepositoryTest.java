package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Fine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FineRepositoryTest {

    @Mock
    private FineRepository repo;

    @Test
    public void probarGuardarYBuscar() {
        Fine fine = new Fine();
        fine.setId(1L);
        fine.setMonto(3500.0);
        
        when(repo.save(any(Fine.class))).thenReturn(fine);
        when(repo.findById(1L)).thenReturn(Optional.of(fine));

        Fine guardado = repo.save(fine);
        assertNotNull(guardado);
        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    public void probarFindAll() {
        List<Fine> listaMock = new ArrayList<>();
        listaMock.add(new Fine());
        
        when(repo.findAll()).thenReturn(listaMock);
        assertFalse(repo.findAll().isEmpty());
    }
}