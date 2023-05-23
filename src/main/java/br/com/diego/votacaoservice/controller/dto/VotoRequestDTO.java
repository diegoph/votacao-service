package br.com.diego.votacaoservice.controller.dto;

import br.com.diego.votacaoservice.domain.OpcaoVoto;
import jakarta.validation.constraints.NotNull;

public record VotoRequestDTO(
        @NotNull Long idAssociado,
        @NotNull OpcaoVoto opcaoVoto
) {}