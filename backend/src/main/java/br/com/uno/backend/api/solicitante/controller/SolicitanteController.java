package br.com.uno.backend.api.solicitante.controller;

import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SolicitanteController {
    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping("/api/solicitantes/{cnpj}")
    public ResponseEntity<Solicitante> procurarSolicitantePeloCnpj(@PathVariable String cnpj) throws SolicitanteNotFoundException {
        Optional<Solicitante> solicitante = solicitanteService.procurarSolicitantePeloCnpj(cnpj);
        return ResponseEntity.status(HttpStatus.OK).body(solicitante.get());
    }
}
