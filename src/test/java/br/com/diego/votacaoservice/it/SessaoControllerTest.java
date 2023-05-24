package br.com.diego.votacaoservice.it;

import br.com.diego.votacaoservice.controller.dto.SessaoRequestDTO;
import br.com.diego.votacaoservice.controller.dto.VotoRequestDTO;
import br.com.diego.votacaoservice.controller.mappers.VotoMapper;
import br.com.diego.votacaoservice.domain.Pauta;
import br.com.diego.votacaoservice.domain.Sessao;
import br.com.diego.votacaoservice.job.SessaoResultadoJob;
import br.com.diego.votacaoservice.service.PautaService;
import br.com.diego.votacaoservice.service.SessaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static br.com.diego.votacaoservice.domain.OpcaoVoto.SIM;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@MockBean(SessaoResultadoJob.class)
@SpringBootTest
public class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoMapper votoMapper;

    private static final String URL = "/v1/sessoes";

    @Test
    @Transactional
    @Rollback(true)
    public void testListaSessoes() throws Exception {
        Sessao sessao = criaObjetoSessao(10);
        this.mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].id", notNullValue()))
                .andExpect(jsonPath("$[*].dataAbertura", hasItem(sessao.getDataAbertura().toString())))
                .andExpect(jsonPath("$[*].dataFechamento", hasItem(sessao.getDataFechamento().toString())))
                .andExpect(jsonPath("$[*].idPauta", hasItem(sessao.getPauta().getId().intValue())))
                .andExpect(jsonPath("$[*].pauta", hasItem(sessao.getPauta().getTitulo())));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCriarSessaoVotacao() throws Exception {
        Pauta pauta = criaObjetoPauta();
        SessaoRequestDTO dto = new SessaoRequestDTO(pauta.getId(), 10);
        this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.dataAbertura", notNullValue()))
                .andExpect(jsonPath("$.dataFechamento", notNullValue()))
                .andExpect(jsonPath("$.idPauta", notNullValue()))
                .andExpect(jsonPath("$.pauta", is(pauta.getTitulo())));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRealizarVotoSessao() throws Exception {
        Sessao sessao = criaObjetoSessao(null);
        VotoRequestDTO dto = new VotoRequestDTO(1L, SIM);
        this.mockMvc.perform(post(URL + "/" + sessao.getId() + "/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.voto", is(SIM.toString())));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRealizarVotoSessao_VotoDuplicadoException() throws Exception {
        Sessao sessao = criaObjetoSessao(null);
        VotoRequestDTO dto = new VotoRequestDTO(1L, SIM);
        sessaoService.votar(sessao.getId(), votoMapper.fromRequestDtoToEntity(dto));

        this.mockMvc.perform(post(URL + "/" + sessao.getId() + "/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("VOTO_DUPLICADO")));
    }

    private Pauta criaObjetoPauta() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        return pautaService.cadastrarPauta(pauta);
    }

    private Sessao criaObjetoSessao(Integer duracao) {
        Pauta pauta = new Pauta();
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pautaService.cadastrarPauta(pauta);
        return sessaoService.abrirSessao(pauta.getId(), duracao);
    }
}
