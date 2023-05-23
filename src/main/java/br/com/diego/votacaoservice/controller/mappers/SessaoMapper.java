package br.com.diego.votacaoservice.controller.mappers;

import br.com.diego.votacaoservice.controller.dto.SessaoResponseDTO;
import br.com.diego.votacaoservice.domain.Sessao;

public interface SessaoMapper {

    SessaoResponseDTO fromEntityToResponseDto(Sessao sessao);

}
