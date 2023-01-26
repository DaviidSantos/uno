package br.com.uno.backend.api.solicitante.service;

import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.repository.SolicitanteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitanteService {
    private final SolicitanteRepository solicitanteRepository;

    public SolicitanteService(SolicitanteRepository solicitanteRepository) {
        this.solicitanteRepository = solicitanteRepository;
    }

    public Optional<Solicitante> procurarSolicitantePeloCnpj(String cnpj) throws SolicitanteNotFoundException {
        if (solicitanteRepository.findById(cnpj).isEmpty()) {
            throw new SolicitanteNotFoundException();
        }

        return solicitanteRepository.findById(cnpj);
    }
}
