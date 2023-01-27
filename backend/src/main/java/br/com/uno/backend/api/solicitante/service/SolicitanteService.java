package br.com.uno.backend.api.solicitante.service;

import br.com.uno.backend.api.solicitante.dto.SolicitanteDto;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.api.solicitante.exceptions.CnpjJaCadastradoException;
import br.com.uno.backend.api.solicitante.exceptions.SolicitanteNotFoundException;
import br.com.uno.backend.api.solicitante.repository.SolicitanteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    public List<Solicitante> listarSolicitantes() {
        return solicitanteRepository.findAll();
    }

    @Transactional
    public Solicitante cadastrarSolicitante(SolicitanteDto solicitanteDto) throws CnpjJaCadastradoException {
        if (solicitanteRepository.findById(solicitanteDto.getCnpj()).isPresent()) {
            throw new CnpjJaCadastradoException();
        }

        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDto, solicitante);
        return solicitanteRepository.save(solicitante);
    }
}
