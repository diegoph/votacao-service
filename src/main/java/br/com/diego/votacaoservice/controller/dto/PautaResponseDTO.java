package br.com.diego.votacaoservice.controller.dto;

public record PautaResponseDTO(
        Long id,
        String titulo,
        String descricao
){}