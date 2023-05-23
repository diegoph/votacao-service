package br.com.diego.votacaoservice.service;

import br.com.diego.votacaoservice.domain.Pauta;
import br.com.diego.votacaoservice.exception.PautaNaoEncontradaException;
import br.com.diego.votacaoservice.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.diego.votacaoservice.utils.PautaConstants.PAUTA_NAO_ENCONTRADA_EXCEPTION;

@Service
@Transactional
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta cadastrarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Transactional(readOnly = true)
    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pauta buscar(Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        return pauta.orElseThrow(() -> new PautaNaoEncontradaException(PAUTA_NAO_ENCONTRADA_EXCEPTION));
    }
}
