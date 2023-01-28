package br.com.uno.backend.controllers.solicitante;

import br.com.uno.backend.api.solicitante.controller.SolicitanteController;
import br.com.uno.backend.api.solicitante.dto.SolicitanteDto;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.CnpjJaCadastradoException;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SolicitanteController.class)
public class SolicitanteControllerTest {
    @MockBean
    SolicitanteService solicitanteService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Procurar Solicitante Pelo CNPJ")
    public void procurarSolicitantePeloCnpj() throws Exception {
        Solicitante solicitante = new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste");
        when(solicitanteService.procurarSolicitantePeloCnpj("12345678912345")).thenReturn(Optional.of(solicitante));

        mockMvc.perform(get("/api/solicitantes/{cnpj}", "12345678912345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is("12345678912345")))
                .andExpect(jsonPath("$.nomeFantasia", is("Solicitante Teste")))
                .andExpect(jsonPath("$.endererco", is("Endereço Teste")));
    }

    @Test
    @DisplayName("Procurar Solicitante Pelo CNPJ Quando Solicitante não Existe")
    public void procurarSolicitantePeloCnpj_QuandoSolicitanteNaoExiste() throws Exception {
        when(solicitanteService.procurarSolicitantePeloCnpj("12345678912345")).thenThrow(SolicitanteNotFoundException.class);

        mockMvc.perform(get("/api/solicitantes/{cnpj}", "12345678912345"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Listar Solicitantes")
    public void listarSolicitantes() throws Exception {
        when(solicitanteService.listarSolicitantes()).thenReturn(Arrays.asList(
                new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste"),
                new Solicitante("12345678912346", "Solicitante Teste2", "Endereço Teste2"),
                new Solicitante("12345678912347", "Solicitante Teste3", "Endereço Teste3")
        ));

        mockMvc.perform(get("/api/solicitantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].cnpj", is("12345678912345")))
                .andExpect(jsonPath("$[1].cnpj", is("12345678912346")))
                .andExpect(jsonPath("$[2].cnpj", is("12345678912347")))
                .andExpect(jsonPath("$[0].nomeFantasia", is("Solicitante Teste")))
                .andExpect(jsonPath("$[1].nomeFantasia", is("Solicitante Teste2")))
                .andExpect(jsonPath("$[2].nomeFantasia", is("Solicitante Teste3")))
                .andExpect(jsonPath("$[0].endererco", is("Endereço Teste")))
                .andExpect(jsonPath("$[1].endererco", is("Endereço Teste2")))
                .andExpect(jsonPath("$[2].endererco", is("Endereço Teste3")));
    }

    @Test
    @DisplayName("Cadastrar Solicitante")
    public void cadastrarSolicitante() throws Exception {
        SolicitanteDto solicitanteDto = new SolicitanteDto("12345678912345", "Solicitante Teste","Endereço Teste");
        Solicitante solicitante = new Solicitante("12345678912345", "Solicitante Teste","Endereço Teste");
        when(solicitanteService.cadastrarSolicitante(any())).thenReturn(solicitante);

        mockMvc.perform(post("/api/solicitantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitanteDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", is("12345678912345")))
                .andExpect(jsonPath("$.nomeFantasia", is("Solicitante Teste")))
                .andExpect(jsonPath("$.endererco", is("Endereço Teste")));

    }

    @Test
    @DisplayName("Cadastrar Solicitante Quando Solicitante Já Está Cadastrado")
    public void cadastrarSolicitante_QuandoSolicitanteJaCadastrado() throws Exception {
        when(solicitanteService.cadastrarSolicitante(any())).thenThrow(CnpjJaCadastradoException.class);
        SolicitanteDto solicitanteDto = new SolicitanteDto("12345678912345", "Solicitante Teste","Endereço Teste");
        mockMvc.perform(post("/api/solicitantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitanteDto)))
                .andExpect(status().isConflict());

    }
}
