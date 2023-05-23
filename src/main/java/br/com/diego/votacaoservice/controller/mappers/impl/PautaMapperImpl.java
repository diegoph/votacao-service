package br.com.diego.votacaoservice.controller.mappers.impl;

import br.com.diego.votacaoservice.controller.dto.PautaRequestDTO;
import br.com.diego.votacaoservice.controller.dto.PautaResponseDTO;
import br.com.diego.votacaoservice.controller.mappers.PautaMapper;
import br.com.diego.votacaoservice.domain.Pauta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PautaMapperImpl implements PautaMapper {
    @Override
    public PautaResponseDTO fromEntityToResponseDto(Pauta pauta) {
        if (pauta == null) {
            return null;
        }
        return new PautaResponseDTO(pauta.getId(), pauta.getTitulo(), pauta.getDescricao());
    }

    @Override
    public List<PautaResponseDTO> fromEntityListToResponseDto(List<Pauta> pautas) {
        if (pautas == null || pautas.isEmpty()) {
            return List.of();
        }
        List<PautaResponseDTO> pautasDto = new ArrayList<>();
        for (Pauta pauta : pautas) {
            pautasDto.add(new PautaResponseDTO(pauta.getId(), pauta.getTitulo(), pauta.getDescricao()));
        }
        return pautasDto;
    }

    @Override
    public Pauta fromRequestDtoToEntity(PautaRequestDTO pautaRequestDTO) {
        if (pautaRequestDTO == null) {
            return null;
        }

        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaRequestDTO.titulo());
        pauta.setDescricao(pautaRequestDTO.descricao());

        return pauta;
    }
}
