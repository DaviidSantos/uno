package br.com.uno.backend.services.solicitante;

import br.com.uno.backend.api.solicitante.dto.SolicitanteDto;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.CnpjJaCadastradoException;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.repository.SolicitanteRepository;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
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

    @Test
    @DisplayName("Procurar Solicitante Pelo Cnpj")
    public void procurarSolicitantePeloCnpj() throws SolicitanteNotFoundException {
        when(solicitanteRepository.findById("12345678912345"))
                .thenReturn(Optional.of(new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste")));
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

    @Test
    @DisplayName("Procurar Lista de Solicitantes")
    public void listarSolicitantes() {
        when(solicitanteRepository.findAll()).thenReturn(Arrays.asList(
                new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste"),
                new Solicitante("12345678912346", "Solicitante Teste2", "Endereço Teste2"),
                new Solicitante("12345678912347", "Solicitante Teste3", "Endereço Teste3")
        ));

        List<Solicitante> solicitantes = solicitanteService.listarSolicitantes();
        assertEquals(solicitantes, solicitanteRepository.findAll());
    }

    @Test
    @DisplayName("Cadastrar Solicitante")
    public void cadastrarSolicitante() throws CnpjJaCadastradoException {
        when(solicitanteRepository.save(new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste")))
                .thenReturn(new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste"));

        SolicitanteDto solicitanteDto = new SolicitanteDto("12345678912345", "Solicitante Teste", "Endereço Teste");
        Solicitante solicitante = solicitanteService.cadastrarSolicitante(solicitanteDto);
        assertEquals(solicitante, solicitanteRepository.save(new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste")));
    }

    @Test
    @DisplayName("Cadastrar Solicitante Quando CNPJ já existe")
    public void cadastrarSolicitante_QuandoCnpjJaExiste() {
        when(solicitanteRepository.findById("12345678912346"))
                .thenReturn(Optional.of(new Solicitante("12345678912346", "Solicitante Teste", "Endereço Teste")));

        assertThrows(CnpjJaCadastradoException.class, () -> {
            SolicitanteDto solicitanteDto = new SolicitanteDto("12345678912346", "Solicitante Teste", "Endereço Teste");
            Solicitante solicitante = solicitanteService.cadastrarSolicitante(solicitanteDto);
        });
    }
}
