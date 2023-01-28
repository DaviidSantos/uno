package br.com.uno.backend.api.solicitacaodeanalise.service;

import br.com.uno.backend.api.solicitacaodeanalise.entidade.SolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.exceptions.SolicitacaoDeAnaliseNotFound;
import br.com.uno.backend.api.solicitacaodeanalise.repository.SolicitacaoDeAnaliseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoDeAnaliseService {
    private final SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository;

    public SolicitacaoDeAnaliseService(SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository) {
        this.solicitacaoDeAnaliseRepository = solicitacaoDeAnaliseRepository;
    }

    public Optional<SolicitacaoDeAnalise> procurarSolicitacaoDeAnalise(String id) throws SolicitacaoDeAnaliseNotFound {
        if (solicitacaoDeAnaliseRepository.findById(id).isEmpty()) {
            throw new SolicitacaoDeAnaliseNotFound();
        }

        return solicitacaoDeAnaliseRepository.findById(id);
    }

    public List<SolicitacaoDeAnalise> listarSolicitacoesDeAnalise() {
        return solicitacaoDeAnaliseRepository.findAll();
    }
}
