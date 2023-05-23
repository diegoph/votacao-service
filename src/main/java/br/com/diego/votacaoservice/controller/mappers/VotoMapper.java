package br.com.diego.votacaoservice.controller.mappers;

import br.com.diego.votacaoservice.controller.dto.VotoRequestDTO;
import br.com.diego.votacaoservice.controller.dto.VotoResponseDTO;
import br.com.diego.votacaoservice.domain.Voto;

public interface VotoMapper {

    Voto fromRequestDtoToEntity(VotoRequestDTO votoRequestDTO);
    VotoResponseDTO fromEntityToResponseDTO(Voto voto);

}
