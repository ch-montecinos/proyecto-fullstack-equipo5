package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Loan;
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
public class LoanRepositoryTest {

    @Mock
    private LoanRepository repo;

    @Test
    public void probarGuardarYBuscar() {
        Loan loan = new Loan();
        loan.setId(1L);
        
        when(repo.save(any(Loan.class))).thenReturn(loan);
        when(repo.findById(1L)).thenReturn(Optional.of(loan));

        Loan guardado = repo.save(loan);
        assertNotNull(guardado);
        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    public void probarFindAll() {
        List<Loan> listaMock = new ArrayList<>();
        listaMock.add(new Loan());
        
        when(repo.findAll()).thenReturn(listaMock);
        assertFalse(repo.findAll().isEmpty());
    }
}