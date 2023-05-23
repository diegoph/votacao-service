package br.com.diego.votacaoservice.domain;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idSessao;

    @Column
    private Long idAssociado;

    @Enumerated(EnumType.STRING)
    @Column(name = "voto", columnDefinition = "voto_enum")
    @Type(PostgreSQLEnumType.class)
    private OpcaoVoto opcaoVoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public OpcaoVoto getOpcaoVoto() {
        return opcaoVoto;
    }

    public void setOpcaoVoto(OpcaoVoto opcaoVoto) {
        this.opcaoVoto = opcaoVoto;
    }
}
