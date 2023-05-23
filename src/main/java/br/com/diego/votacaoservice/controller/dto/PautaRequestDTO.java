package br.com.diego.votacaoservice.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import static br.com.diego.votacaoservice.utils.PautaConstants.TITULO_TAMANHO_MAXIMO;

public record PautaRequestDTO (
        @NotEmpty @Size(max = TITULO_TAMANHO_MAXIMO) String titulo,
        @NotEmpty String descricao
) {}