package br.com.diego.votacaoservice.service;

import br.com.diego.votacaoservice.domain.ResultadoVotacao;
import br.com.diego.votacaoservice.domain.Voto;
import br.com.diego.votacaoservice.exception.VotoDuplicadoException;
import br.com.diego.votacaoservice.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.diego.votacaoservice.utils.SessaoConstants.VOTO_DUPLICADO_EXCEPTION;

@Service
@Transactional
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    public Voto registrarVoto(Voto voto) {
        if (hasVoted(voto)) {
            throw new VotoDuplicadoException(VOTO_DUPLICADO_EXCEPTION);
        }
        votoRepository.save(voto);
        return voto;
    }

    @Transactional(readOnly = true)
    public ResultadoVotacao obterResultadoVotacao(Long idSessao) {
        return votoRepository.getResultado(idSessao);
    }

    @Transactional(readOnly = true)
    public boolean hasVoted(Voto voto) {
        return votoRepository.existsByIdSessaoAndIdAssociado(voto.getIdSessao(), voto.getIdAssociado());
    }
}
