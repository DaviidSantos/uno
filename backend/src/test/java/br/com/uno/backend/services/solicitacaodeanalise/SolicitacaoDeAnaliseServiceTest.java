package br.com.uno.backend.services.solicitacaodeanalise;

import br.com.uno.backend.api.solicitacaodeanalise.dto.SolicitacaoDeAnaliseDto;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.SolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.StatusSolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.TipoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.exceptions.SolicitacaoDeAnaliseNotFound;
import br.com.uno.backend.api.solicitacaodeanalise.repository.SolicitacaoDeAnaliseRepository;
import br.com.uno.backend.api.solicitacaodeanalise.service.SolicitacaoDeAnaliseService;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SolicitacaoDeAnaliseServiceTest {
    @Mock
    private SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository;

    @Mock
    private SolicitanteService solicitanteService;

    @InjectMocks
    private SolicitacaoDeAnaliseService solicitacaoDeAnaliseService;

    @Test
    @DisplayName("Procurar Solicitação de Análise pelo ID")
    public void procurarSolicitacaoDeAnalisePeloId() throws SolicitacaoDeAnaliseNotFound {
        Solicitante solicitante = new Solicitante("12345678912345", "Teste", "Teste");
        SolicitacaoDeAnalise solicitacaoDeAnalise = new SolicitacaoDeAnalise(
                "SA_00001",
                solicitante,
                TipoDeAnalise.Solubilidade,
                StatusSolicitacaoDeAnalise.Em_Andamento,
                "Teste",
                "Teste",
                "Responsavel Teste",
                "Email Teste",
                "Telefone Teste"
        );
        when(solicitacaoDeAnaliseRepository.findById("SA_00001")).thenReturn(Optional.of(solicitacaoDeAnalise));
        Optional<SolicitacaoDeAnalise> solicitacaoDeAnalise1 = solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise("SA_00001");
        assertEquals(solicitacaoDeAnalise1.get(), solicitacaoDeAnalise);
    }

    @Test
    @DisplayName("Procurar Solicitação de Análise pelo ID Quando Solicitação de Análise não existe")
    public void procurarSolicitacaoDeAnalise_QuandoSolicitacaoDeAnaliseNaoExiste() {
        when(solicitacaoDeAnaliseRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(SolicitacaoDeAnaliseNotFound.class, () -> {
            Optional<SolicitacaoDeAnalise> solicitacaoDeAnalise = solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise("SA_00001");
        });
    }

    @Test
    @DisplayName("Listar Solicitações de Análise")
    public void listarSolicitacoesDeAnalise() {
        when(solicitacaoDeAnaliseRepository.findAll()).thenReturn(Arrays.asList(
                new SolicitacaoDeAnalise("SA_00001",
                        null,
                        TipoDeAnalise.Solubilidade,
                        StatusSolicitacaoDeAnalise.Em_Andamento,
                        "Teste",
                        "Teste",
                        "Responsavel Teste",
                        "Email Teste",
                        "Telefone Teste"),
                new SolicitacaoDeAnalise("SA_00002",
                        null,
                        TipoDeAnalise.Solubilidade,
                        StatusSolicitacaoDeAnalise.Em_Andamento,
                        "Teste",
                        "Teste",
                        "Responsavel Teste",
                        "Email Teste",
                        "Telefone Teste")
        ));

        List<SolicitacaoDeAnalise> solicitacoesDeAnalise = solicitacaoDeAnaliseService.listarSolicitacoesDeAnalise();
        assertEquals(solicitacoesDeAnalise, solicitacaoDeAnaliseRepository.findAll());
    }

    @Test
    @DisplayName("Cadastrar Solicitação de Análise")
    public void cadastrarSolicitacaoDeAnalise() throws SolicitanteNotFoundException {
        when(solicitacaoDeAnaliseRepository.save(any())).thenReturn(
                new SolicitacaoDeAnalise(
                        "SA_00001",
                        null,
                        TipoDeAnalise.Solubilidade,
                        StatusSolicitacaoDeAnalise.Em_Andamento,
                        "Testes",
                        "Teste",
                        "Responsavel Teste",
                        "Email Teste",
                        "Telefone Teste")
        );

        when(solicitanteService.procurarSolicitantePeloCnpj(any())).thenReturn(Optional.of(new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste")));

        SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto = new SolicitacaoDeAnaliseDto(
                null,
                "Solubilidade",
                "Em_Andamento",
                "Teste",
                "Teste",
                "Responsavel Teste",
                "Email Teste",
                "Telefone Teste"
        );

        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.cadastrarSolicitacaoDeAnalise(solicitacaoDeAnaliseDto);
        Assertions.assertNotNull(solicitacaoDeAnalise);
        assertEquals("SA_00001", solicitacaoDeAnalise.getId());
    }

    @Test
    @DisplayName("Cadastrar Solicitação de Análise quando Solicitante não Existe")
    public void cadastrarSolicitacaoDeAnalise_QuandoSolicitanteNaoExiste() throws SolicitanteNotFoundException {
        when(solicitanteService.procurarSolicitantePeloCnpj(any())).thenReturn(Optional.empty());

        SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto = new SolicitacaoDeAnaliseDto(
                null,
                "Solubilidade",
                "Em_Andamento",
                "Teste",
                "Teste",
                "Responsavel Teste",
                "Email Teste",
                "Telefone Teste"
        );

        assertThrows(SolicitanteNotFoundException.class, () -> {
            SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.cadastrarSolicitacaoDeAnalise(solicitacaoDeAnaliseDto);
        });
    }

    @Test
    @DisplayName("Atualizar Status da Solicitção De Análise")
    public void atualizarStatusSolicitaçãoDeAnalise() throws SolicitacaoDeAnaliseNotFound {
        when(solicitacaoDeAnaliseRepository.findById(any())).thenReturn(
                Optional.of(new SolicitacaoDeAnalise(
                        "SA_00001",
                        null,
                        TipoDeAnalise.Degradação_Forçada,
                        StatusSolicitacaoDeAnalise.Em_Andamento,
                        "Testes",
                        "Teste",
                        "Responsavel Teste",
                        "Email Teste",
                        "Telefone Teste"))
        );

        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.atualizarStatusSolicitacaoDeAnalise("SA_00001", "Concluida");
        assertEquals(StatusSolicitacaoDeAnalise.Concluida, solicitacaoDeAnalise.getStatusSolicitacaoDeAnalise());
    }

    @Test
    @DisplayName("Atualizar Solicitação de Análise Quando Solicitação de Análise Não Existe")
    public void atualizarStatusSolicitacaoDeAnalise_QuandoSolicitacaoDeAnaliseNaoExiste() {
        when(solicitacaoDeAnaliseRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(SolicitacaoDeAnaliseNotFound.class, () -> {
            SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.atualizarStatusSolicitacaoDeAnalise("SA_00001", "Concluida");
        });
    }
}
