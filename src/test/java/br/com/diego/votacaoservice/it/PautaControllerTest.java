package br.com.diego.votacaoservice.it;

import br.com.diego.votacaoservice.controller.dto.PautaRequestDTO;
import br.com.diego.votacaoservice.domain.Pauta;
import br.com.diego.votacaoservice.job.SessaoResultadoJob;
import br.com.diego.votacaoservice.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@MockBean(SessaoResultadoJob.class)
@SpringBootTest
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PautaService pautaService;

    private static final String URL = "/v1/pautas";

    @Test
    @Transactional
    public void testListaPautas() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pautaService.cadastrarPauta(pauta);
        this.mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].id", notNullValue()))
                .andExpect(jsonPath("$[*].titulo", hasItem(pauta.getTitulo())))
                .andExpect(jsonPath("$[*].descricao", hasItem(pauta.getDescricao())));
    }

    @Test
    @Transactional
    public void testCadastrarPautas() throws Exception {
        PautaRequestDTO dto = new PautaRequestDTO("titulo teste", "descricao teste");
        this.mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.titulo", is(dto.titulo())))
                .andExpect(jsonPath("$.descricao", is(dto.descricao())));
    }

}
