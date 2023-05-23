package br.com.diego.votacaoservice.controller.mappers.impl;

import br.com.diego.votacaoservice.controller.dto.VotoRequestDTO;
import br.com.diego.votacaoservice.controller.dto.VotoResponseDTO;
import br.com.diego.votacaoservice.controller.mappers.VotoMapper;
import br.com.diego.votacaoservice.domain.Voto;
import org.springframework.stereotype.Component;

@Component
public class VotoMapperImpl implements VotoMapper {

    @Override
    public Voto fromRequestDtoToEntity(VotoRequestDTO votoRequestDTO) {
        if (votoRequestDTO == null) {
            return null;
        }

        Voto voto = new Voto();
        voto.setIdAssociado(votoRequestDTO.idAssociado());
        voto.setOpcaoVoto(votoRequestDTO.opcaoVoto());

        return voto;
    }

    @Override
    public VotoResponseDTO fromEntityToResponseDTO(Voto voto) {
        if (voto == null) {
            return null;
        }
        return new VotoResponseDTO(voto.getId(), voto.getOpcaoVoto());
    }

}
