package br.com.diego.votacaoservice.controller;

import br.com.diego.votacaoservice.controller.dto.PautaRequestDTO;
import br.com.diego.votacaoservice.controller.dto.PautaResponseDTO;
import br.com.diego.votacaoservice.controller.mappers.PautaMapper;
import br.com.diego.votacaoservice.domain.Pauta;
import br.com.diego.votacaoservice.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired
    private PautaMapper pautaMapper;

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaResponseDTO> cadastraPauta(@Valid @RequestBody PautaRequestDTO dto) {
        Pauta pauta = pautaService.cadastrarPauta(pautaMapper.fromRequestDtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaMapper.fromEntityToResponseDto(pauta));
    }

    @GetMapping
    public ResponseEntity<List<PautaResponseDTO>> listaPautas() {
        List<Pauta> pautas = pautaService.listarPautas();
        return ResponseEntity.ok(pautaMapper.fromEntityListToResponseDto(pautas));
    }

}
