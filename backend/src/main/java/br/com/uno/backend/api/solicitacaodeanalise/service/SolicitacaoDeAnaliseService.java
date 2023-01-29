package br.com.uno.backend.api.solicitacaodeanalise.service;

import br.com.uno.backend.api.solicitacaodeanalise.dto.SolicitacaoDeAnaliseDto;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.SolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.StatusSolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.TipoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.exceptions.SolicitacaoDeAnaliseNotFound;
import br.com.uno.backend.api.solicitacaodeanalise.repository.SolicitacaoDeAnaliseRepository;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.service.SolicitanteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoDeAnaliseService {
    private final SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository;
    private final SolicitanteService solicitanteService;

    public SolicitacaoDeAnaliseService(SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository, SolicitanteService solicitanteService) {
        this.solicitacaoDeAnaliseRepository = solicitacaoDeAnaliseRepository;
        this.solicitanteService = solicitanteService;
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

    @Transactional
    public SolicitacaoDeAnalise cadastrarSolicitacaoDeAnalise(SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto) throws SolicitanteNotFoundException {
        if (solicitanteService.procurarSolicitantePeloCnpj(solicitacaoDeAnaliseDto.getCnpjSolicitante()).isEmpty()) {
            throw new SolicitanteNotFoundException();
        }

        SolicitacaoDeAnalise solicitacaoDeAnalise = new SolicitacaoDeAnalise();
        Solicitante solicitante = solicitanteService.procurarSolicitantePeloCnpj(solicitacaoDeAnaliseDto.getCnpjSolicitante()).get();
        BeanUtils.copyProperties(solicitacaoDeAnaliseDto, solicitacaoDeAnalise);
        solicitacaoDeAnalise.setSolicitante(solicitante);
        solicitacaoDeAnalise.setTipoDeAnalise(TipoDeAnalise.valueOf(solicitacaoDeAnaliseDto.getTipoDeAnalise()));
        solicitacaoDeAnalise.setStatusSolicitacaoDeAnalise(StatusSolicitacaoDeAnalise.valueOf(solicitacaoDeAnaliseDto.getStatusSolicitacaoDeAnalise()));
        return solicitacaoDeAnaliseRepository.save(solicitacaoDeAnalise);
    }

    @Transactional
    public SolicitacaoDeAnalise atualizarStatusSolicitacaoDeAnalise(String id, String status) {
        Optional<SolicitacaoDeAnalise> solicitacaoDeAnalise = solicitacaoDeAnaliseRepository.findById(id);
        solicitacaoDeAnalise.get().setStatusSolicitacaoDeAnalise(StatusSolicitacaoDeAnalise.valueOf(status));
        return solicitacaoDeAnalise.get();
    }
}
