package br.com.diego.votacaoservice.job;

import br.com.diego.votacaoservice.domain.ResultadoVotacao;
import br.com.diego.votacaoservice.domain.Sessao;
import br.com.diego.votacaoservice.service.SessaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessaoResultadoJob {

    Logger logger = LoggerFactory.getLogger(SessaoResultadoJob.class);

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private KafkaTemplate<String, ResultadoVotacao> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${kafka.topico.resultado}")
    private String topicoResultado;

    @Scheduled(fixedDelay = 60000)
    public void enviarResultadosSessoesEncerradas() {
        logger.info("Iniciando JOB de envio de resultados");
        List<Sessao> sessoesEncerradas = sessaoService.buscarSessoesEncerradasNaoPublicadas(LocalDateTime.now());
        logger.info("Quantidade pendente de envio: {}", sessoesEncerradas.size());
        for (Sessao sessao : sessoesEncerradas) {
            ResultadoVotacao resultadoVotacao = sessaoService.obterResultadoVotacao(sessao.getId());
            logger.info("Enviando resultado: {}", resultadoVotacao);
            kafkaTemplate.send(topicoResultado, String.valueOf(sessao.getId()), resultadoVotacao);
            sessaoService.fecharSessao(sessao);
        }
        logger.info("Processamento finalizado");
    }

}

