package br.com.diego.votacaoservice.controller;

import br.com.diego.votacaoservice.controller.dto.SessaoRequestDTO;
import br.com.diego.votacaoservice.controller.dto.SessaoResponseDTO;
import br.com.diego.votacaoservice.controller.dto.VotoRequestDTO;
import br.com.diego.votacaoservice.controller.dto.VotoResponseDTO;
import br.com.diego.votacaoservice.controller.mappers.SessaoMapper;
import br.com.diego.votacaoservice.controller.mappers.VotoMapper;
import br.com.diego.votacaoservice.domain.ResultadoVotacao;
import br.com.diego.votacaoservice.domain.Sessao;
import br.com.diego.votacaoservice.domain.Voto;
import br.com.diego.votacaoservice.service.SessaoService;
import br.com.diego.votacaoservice.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private VotoService votoService;

    @Autowired
    private VotoMapper votoMapper;

    @Autowired
    private SessaoMapper sessaoMapper;

    @PostMapping
    public ResponseEntity<SessaoResponseDTO> abreSessao(@Valid @RequestBody SessaoRequestDTO sessaoRequestDTO) {
        Sessao sessao = sessaoService.abrirSessao(sessaoRequestDTO.idPauta(), sessaoRequestDTO.duracaoEmMinutos());
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoMapper.fromEntityToResponseDto(sessao));
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> listaSessao() {
        List<Sessao> sessoes = sessaoService.listarSessoes();
        return ResponseEntity.ok(sessaoMapper.fromEntityListToResponseDto(sessoes));
    }

    @PostMapping("/{idSessao}/votos")
    public ResponseEntity<VotoResponseDTO> realizaVoto(@PathVariable Long idSessao,
                                                       @Valid @RequestBody VotoRequestDTO votoRequestDTO) {
        Voto voto = sessaoService.votar(idSessao, votoMapper.fromRequestDtoToEntity(votoRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(votoMapper.fromEntityToResponseDTO(voto));
    }

    @GetMapping("/{idSessao}/resultado")
    public ResponseEntity<ResultadoVotacao> obtemResultado(@PathVariable Long idSessao) {
        ResultadoVotacao resultado = sessaoService.obterResultadoVotacao(idSessao);
        return ResponseEntity.ok(resultado);
    }

}
