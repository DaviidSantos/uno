package br.com.uno.backend.api.solicitacaodeanalise.dto;

import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.StatusSolicitacaoDeAnalise;
import br.com.uno.backend.api.solicitacaodeanalise.entidade.enums.TipoDeAnalise;
import br.com.uno.backend.api.solicitante.entidade.Solicitante;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SolicitacaoDeAnaliseDto {
    private String cnpjSolicitante;
    private String tipoDeAnalise;
    private String statusSolicitacaoDeAnalise;
    private String consideracoesGerais;
    private String informacoesAdicionais;
    private String nomeResponsavel;
    private String emailResponsavel;
    private String telefoneResponsavel;

    public SolicitacaoDeAnaliseDto() {
    }

    public SolicitacaoDeAnaliseDto(String cnpjSolicitante, String tipoDeAnalise, String statusSolicitacaoDeAnalise, String consideracoesGerais, String informacoesAdicionais, String nomeResponsavel, String emailResponsavel, String telefoneResponsavel) {
        this.cnpjSolicitante = cnpjSolicitante;
        this.tipoDeAnalise = tipoDeAnalise;
        this.statusSolicitacaoDeAnalise = statusSolicitacaoDeAnalise;
        this.consideracoesGerais = consideracoesGerais;
        this.informacoesAdicionais = informacoesAdicionais;
        this.nomeResponsavel = nomeResponsavel;
        this.emailResponsavel = emailResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getCnpjSolicitante() {
        return cnpjSolicitante;
    }

    public void setCnpjSolicitante(String cnpjSolicitante) {
        this.cnpjSolicitante = cnpjSolicitante;
    }

    public String getTipoDeAnalise() {
        return tipoDeAnalise;
    }

    public void setTipoDeAnalise(String tipoDeAnalise) {
        this.tipoDeAnalise = tipoDeAnalise;
    }

    public String getStatusSolicitacaoDeAnalise() {
        return statusSolicitacaoDeAnalise;
    }

    public void setStatusSolicitacaoDeAnalise(String statusSolicitacaoDeAnalise) {
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
}
