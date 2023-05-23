package br.com.diego.votacaoservice.repository;

import br.com.diego.votacaoservice.domain.ResultadoVotacao;
import br.com.diego.votacaoservice.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByIdSessaoAndIdAssociado(Long idSessao, Long idAssociado);
    @Query("""
       SELECT new br.com.diego.votacaoservice.domain.ResultadoVotacao(
            count(id) as totalVotos,
            count(id) FILTER (WHERE opcaoVoto = 'SIM') as totalSim, 
            count(id) FILTER (WHERE opcaoVoto = 'NAO') as totalNao)
       FROM Voto 
       WHERE idSessao = :idSessao""")
    ResultadoVotacao getResultado(Long idSessao);
}
