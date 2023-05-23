package br.com.diego.votacaoservice.controller.dto;

import jakarta.validation.constraints.NotNull;

public record SessaoRequestDTO (
        @NotNull Long idPauta,
        Integer duracaoEmMinutos
) {}