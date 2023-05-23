package br.com.diego.votacaoservice.domain;

import static br.com.diego.votacaoservice.domain.ResultadoVotacaoStatus.*;

public class ResultadoVotacao {

    private long totalVotos;

    private long totalSim;

    private long totalNao;

    ResultadoVotacaoStatus resultado;

    public ResultadoVotacao(long totalVotos, long totalSim, long totalNao) {
        this.totalVotos = totalVotos;
        this.totalSim = totalSim;
        this.totalNao = totalNao;
    }

    public long getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(long totalVotos) {
        this.totalVotos = totalVotos;
    }

    public long getTotalSim() {
        return totalSim;
    }

    public void setTotalSim(long totalSim) {
        this.totalSim = totalSim;
    }

    public long getTotalNao() {
        return totalNao;
    }

    public void setTotalNao(long totalNao) {
        this.totalNao = totalNao;
    }

    public ResultadoVotacaoStatus getResultado() {
        return resultado;
    }

    public void definirResultado() {
        if (totalSim > totalNao) {
            resultado = APROVADO;
        } else if (totalSim < totalNao) {
            resultado = REPROVADO;
        } else {
            resultado = EMPATE;
        }
    }
}
