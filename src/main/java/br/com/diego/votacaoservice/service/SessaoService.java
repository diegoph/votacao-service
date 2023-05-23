package br.com.diego.votacaoservice.service;

import br.com.diego.votacaoservice.domain.ResultadoVotacao;
import br.com.diego.votacaoservice.domain.Sessao;
import br.com.diego.votacaoservice.domain.Voto;
import br.com.diego.votacaoservice.exception.SessaoAbertaResultadoException;
import br.com.diego.votacaoservice.exception.SessaoFechadaException;
import br.com.diego.votacaoservice.exception.SessaoNaoEncontradaException;
import br.com.diego.votacaoservice.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.diego.votacaoservice.utils.SessaoConstants.*;

@Service
@Transactional
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoService votoService;

    public Sessao abrirSessao(Long idPauta, Integer ducacaoEmMinutos) {
       Sessao sessao = new Sessao();
       sessao.setPauta(pautaService.buscar(idPauta));
       sessao.iniciar(ducacaoEmMinutos);
       sessaoRepository.save(sessao);
       return sessao;
    }

    public Voto votar(Long idSessao, Voto voto) {
        Sessao sessao = buscar(idSessao);
        if (!sessao.isAberta()) {
            throw new SessaoFechadaException(SESSAO_FECHADA_EXCEPTION);
        }
        voto.setIdSessao(idSessao);
        return votoService.registrarVoto(voto);
    }

    @Transactional(readOnly = true)
    public Sessao buscar(Long id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        return sessao.orElseThrow(() -> new SessaoNaoEncontradaException(SESSAO_NAO_ENCONTRADA_EXCEPTION));
    }

    public ResultadoVotacao obterResultadoVotacao(Long idSessao) {
        Sessao sessao = buscar(idSessao);
        if (sessao.isAberta()) {
            throw new SessaoAbertaResultadoException(SESSAO_ABERTA_RESULTADO_EXCEPTION);
        }
        ResultadoVotacao resultadoVotacao = votoService.obterResultadoVotacao(idSessao);
        resultadoVotacao.definirResultado();
        return resultadoVotacao;
    }
}
