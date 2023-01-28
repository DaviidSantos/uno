package br.com.uno.backend.api.solicitacaodeanalise.entidade;

import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.StatusSolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.TipoDeAnalise;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;
import br.com.uno.backend.utils.IdCostumizado;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_SOLICITACAO_DE_ANALISE")
public class SolicitacaoDeAnalise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sa_seq")
    @GenericGenerator(name = "sa_seq", strategy = "br.com.uno.backend.utils.IdCostumizado",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdCostumizado.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdCostumizado.VALUE_PREFIX_PARAMETER, value = "SA_"),
                    @org.hibernate.annotations.Parameter(name = IdCostumizado.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id;
    @ManyToOne
    @JoinColumn(name = "cnpj_solicitante")
    private Solicitante solicitante;
    @Enumerated(EnumType.STRING)
    private TipoDeAnalise tipoDeAnalise;
    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoDeAnalise statusSolicitacaoDeAnalise;
    @Column(columnDefinition = "TEXT")
    private String consideracoesGerais;
    @Column(columnDefinition = "TEXT")
    private String informacoesAdicionais;
    @Column(nullable = false)
    private String nomeResponsavel;
    @Column(nullable = false)
    private String emailResponsavel;
    @Column(nullable = false)
    private String telefoneResponsavel;

    public SolicitacaoDeAnalise() {
    }

    public SolicitacaoDeAnalise(String id, Solicitante solicitante, TipoDeAnalise tipoDeAnalise, StatusSolicitacaoDeAnalise statusSolicitacaoDeAnalise, String consideracoesGerais, String informacoesAdicionais, String nomeResponsavel, String emailResponsavel, String telefoneResponsavel) {
        this.id = id;
        this.solicitante = solicitante;
        this.tipoDeAnalise = tipoDeAnalise;
        this.statusSolicitacaoDeAnalise = statusSolicitacaoDeAnalise;
        this.consideracoesGerais = consideracoesGerais;
        this.informacoesAdicionais = informacoesAdicionais;
        this.nomeResponsavel = nomeResponsavel;
        this.emailResponsavel = emailResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public TipoDeAnalise getTipoDeAnalise() {
        return tipoDeAnalise;
    }

    public void setTipoDeAnalise(TipoDeAnalise tipoDeAnalise) {
        this.tipoDeAnalise = tipoDeAnalise;
    }

    public StatusSolicitacaoDeAnalise getStatusSolicitacaoDeAnalise() {
        return statusSolicitacaoDeAnalise;
    }

    public void setStatusSolicitacaoDeAnalise(StatusSolicitacaoDeAnalise statusSolicitacaoDeAnalise) {
        this.statusSolicitacaoDeAnalise = statusSolicitacaoDeAnalise;
    }

    public String getConsideracoesGerais() {
        return consideracoesGerais;
    }

    public void setConsideracoesGerais(String consideracoesGerais) {
        this.consideracoesGerais = consideracoesGerais;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolicitacaoDeAnalise that = (SolicitacaoDeAnalise) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
