package br.com.uno.backend.api.solicitante.repository;

import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitanteRepository extends JpaRepository<Solicitante, String> {
}
