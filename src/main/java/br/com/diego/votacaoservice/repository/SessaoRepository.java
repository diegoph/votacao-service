package br.com.diego.votacaoservice.repository;

import br.com.diego.votacaoservice.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    List<Sessao> findByDataFechamentoBeforeAndIsResultadoPublicadoIsFalse(LocalDateTime dataAtual);
}
