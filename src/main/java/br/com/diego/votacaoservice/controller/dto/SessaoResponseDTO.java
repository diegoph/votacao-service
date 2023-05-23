package br.com.diego.votacaoservice.controller.dto;

import java.time.LocalDateTime;

public record SessaoResponseDTO(
        Long idSessao,
        LocalDateTime dataAbertura,
        LocalDateTime dataFechamento,
        Long idPauta,
        String pauta
) {}