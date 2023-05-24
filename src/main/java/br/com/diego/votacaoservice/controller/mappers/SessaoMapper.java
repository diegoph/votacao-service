package br.com.diego.votacaoservice.controller.mappers;

import br.com.diego.votacaoservice.controller.dto.SessaoResponseDTO;
import br.com.diego.votacaoservice.domain.Sessao;

import java.util.List;

public interface SessaoMapper {

    SessaoResponseDTO fromEntityToResponseDto(Sessao sessao);

    List<SessaoResponseDTO> fromEntityListToResponseDto(List<Sessao> sessoes);
}
