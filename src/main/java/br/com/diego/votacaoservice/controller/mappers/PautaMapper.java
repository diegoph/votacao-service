package br.com.diego.votacaoservice.controller.mappers;

import br.com.diego.votacaoservice.controller.dto.PautaRequestDTO;
import br.com.diego.votacaoservice.controller.dto.PautaResponseDTO;
import br.com.diego.votacaoservice.domain.Pauta;

import java.util.List;

public interface PautaMapper {

    PautaResponseDTO fromEntityToResponseDto(Pauta pauta);
    List<PautaResponseDTO> fromEntityListToResponseDto(List<Pauta> pautas);
    Pauta fromRequestDtoToEntity(PautaRequestDTO pautaRequestDTO);

}
