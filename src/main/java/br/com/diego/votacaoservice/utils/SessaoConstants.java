package br.com.diego.votacaoservice.utils;

public final class SessaoConstants {

    private SessaoConstants(){};

    public static final Integer SESSAO_DURACAO_MINIMA_EM_MINUTOS = 1;
    public static final String SESSAO_NAO_ENCONTRADA_EXCEPTION = "Sessão não encontrada";
    public static final String SESSAO_FECHADA_EXCEPTION = "A sessão de votação está encerrada";
    public static final String SESSAO_ABERTA_RESULTADO_EXCEPTION = "A sessão ainda está aberta";
    public static final String VOTO_DUPLICADO_EXCEPTION = "O voto já foi computado";
}
