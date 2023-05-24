package br.com.diego.votacaoservice.controller.mappers.impl;

import br.com.diego.votacaoservice.controller.dto.SessaoResponseDTO;
import br.com.diego.votacaoservice.controller.mappers.SessaoMapper;
import br.com.diego.votacaoservice.domain.Sessao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessaoMapperImpl implements SessaoMapper {

    @Override
    public SessaoResponseDTO fromEntityToResponseDto(Sessao sessao) {
        if (sessao == null) {
            return null;
        }
        return new SessaoResponseDTO(sessao.getId(),
                sessao.getDataAbertura(),
                sessao.getDataFechamento(),
                sessao.getPauta().getId(),
                sessao.getPauta().getTitulo());
    }

    @Override
    public List<SessaoResponseDTO> fromEntityListToResponseDto(List<Sessao> sessoes) {
        if (sessoes == null || sessoes.isEmpty()) {
            return List.of();
        }
        List<SessaoResponseDTO> sessoesDto = new ArrayList<>();
        for (Sessao sessao : sessoes) {
            sessoesDto.add(new SessaoResponseDTO(sessao.getId(),
                    sessao.getDataAbertura(),
                    sessao.getDataFechamento(),
                    sessao.getPauta().getId(), sessao.getPauta().getTitulo()));
        }
        return sessoesDto;
    }

}
