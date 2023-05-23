package br.com.diego.votacaoservice.domain;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

import static br.com.diego.votacaoservice.domain.SessaoStatus.ABERTA;
import static br.com.diego.votacaoservice.utils.SessaoConstants.SESSAO_DURACAO_MINIMA_EM_MINUTOS;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "sessao_status_enum")
    @Type(PostgreSQLEnumType.class)
    private SessaoStatus status;

    public Long getId() {
        return id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public SessaoStatus getStatus() {
        return status;
    }

    public void setStatus(SessaoStatus status) {
        this.status = status;
    }

    public void iniciar(Integer duracaoEmMinutos) {
        if (duracaoEmMinutos == null || duracaoEmMinutos < SESSAO_DURACAO_MINIMA_EM_MINUTOS) {
            duracaoEmMinutos = SESSAO_DURACAO_MINIMA_EM_MINUTOS;
        }
        status = ABERTA;
        dataAbertura = LocalDateTime.now();
        dataFechamento = LocalDateTime.now().plusMinutes(duracaoEmMinutos);
    }

    public boolean isAberta() {
        return LocalDateTime.now().isBefore(dataFechamento) && ABERTA == status;
    }
}
