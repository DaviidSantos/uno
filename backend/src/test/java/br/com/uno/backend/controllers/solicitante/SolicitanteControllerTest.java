package br.com.uno.backend.controllers.solicitante;

import br.com.uno.backend.api.solicitante.controller.SolicitanteController;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
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

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SolicitanteController.class)
public class SolicitanteControllerTest {
    @MockBean
    SolicitanteService solicitanteService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Procurar Solicitante Pelo CNPJ controller test")
    public void procurarSolicitantePeloCnpj() throws Exception {
        Solicitante solicitante = new Solicitante("12345678912345", "Solicitante Teste", "Endereço Teste");
        Mockito.when(solicitanteService.procurarSolicitantePeloCnpj("12345678912345")).thenReturn(Optional.of(solicitante));

        mockMvc.perform(get("/api/solicitantes/{cnpj}", "12345678912345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cnpj", Matchers.is("12345678912345")))
                .andExpect(jsonPath("$.nomeFantasia", Matchers.is("Solicitante Teste")))
                .andExpect(jsonPath("$.endererco", Matchers.is("Endereço Teste")));
    }


}
