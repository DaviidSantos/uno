package br.com.uno.backend.api.solicitacaodeanalise.repository;

import br.com.uno.backend.api.solicitacaodeanalise.entidade.SolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoDeAnaliseRepository extends JpaRepository<SolicitacaoDeAnalise, String> {
    List<SolicitacaoDeAnalise> findSolicitacaoDeAnaliseBySolicitante(Solicitante solicitante);
}
