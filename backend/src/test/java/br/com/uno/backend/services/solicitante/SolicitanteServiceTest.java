package br.com.uno.backend.services.solicitante;

import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.repository.SolicitanteRepository;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SolicitanteServiceTest {
    @Mock
    private SolicitanteRepository solicitanteRepository;

    @InjectMocks
    private SolicitanteService solicitanteService;

    @BeforeEach
    void setMockOutput() {
    }

    @Test
    @DisplayName("Procurar Solicitante Pelo Cnpj")
    public void procurarSolicitantePeloCnpj() throws SolicitanteNotFoundException {
        when(solicitanteRepository.findById("12345678912345")).thenReturn(Optional.of(new Solicitante(
                "12345678912345",
                "Solicitante Teste",
                "Endereço Teste")));
        Optional<Solicitante> solicitante = solicitanteService.procurarSolicitantePeloCnpj("12345678912345");
        assertEquals(solicitante, solicitanteRepository.findById("12345678912345"));
    }

    @Test
    @DisplayName("Procurar Solicitante Inexistente")
    public void procurarSolicitantePeloCnpj_QuandoSolicitanteNaoEncontrado() {
        when(solicitanteRepository.findById("12345678912346")).thenReturn(Optional.empty());
        assertThrows(SolicitanteNotFoundException.class, () -> {
            Optional<Solicitante> solicitante = solicitanteService.procurarSolicitantePeloCnpj("12345678912346");
        });
    }
}
