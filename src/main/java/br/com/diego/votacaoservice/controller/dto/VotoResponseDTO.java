package br.com.diego.votacaoservice.controller.dto;

import br.com.diego.votacaoservice.domain.OpcaoVoto;

public record VotoResponseDTO(
        Long id,
        OpcaoVoto voto
) {}