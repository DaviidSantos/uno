package br.com.uno.backend.api.solicitante.controller;

import br.com.uno.backend.api.solicitante.dto.SolicitanteDto;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.CnpjJaCadastradoException;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/api/solicitantes")
    public ResponseEntity<List<Solicitante>> listarSolicitantes() {
        return ResponseEntity.status(HttpStatus.OK).body(solicitanteService.listarSolicitantes());
    }

    @PostMapping("/api/solicitantes")
    public ResponseEntity<Solicitante> cadastrarSolicitante(@RequestBody SolicitanteDto solicitanteDto) throws CnpjJaCadastradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitanteService.cadastrarSolicitante(solicitanteDto));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleSolicitanteNotFoundException(SolicitanteNotFoundException solicitanteNotFoundException) {
        return solicitanteNotFoundException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleCnpjJaCadastradoException(CnpjJaCadastradoException cnpjJaCadastradoException) {
        return cnpjJaCadastradoException.getMessage();
    }
}
