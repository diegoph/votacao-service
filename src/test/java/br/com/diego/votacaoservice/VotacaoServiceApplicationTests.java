package br.com.diego.votacaoservice;

import br.com.diego.votacaoservice.job.SessaoResultadoJob;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@MockBean(SessaoResultadoJob.class)
class VotacaoServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
