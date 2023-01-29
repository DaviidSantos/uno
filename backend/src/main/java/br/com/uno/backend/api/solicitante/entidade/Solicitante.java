package br.com.uno.backend.api.solicitante.entidade;


import br.com.uno.backend.api.solicitacaodeanalise.entidade.SolicitacaoDeAnalise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Solicitante {
    @Id
    @Column(updatable = false, length = 14)
    private String cnpj;
    @Column(nullable = false, name = "nome_fantasia")
    private String nomeFantasia;
    @Column(nullable = false)
    private String endereco;
    @OneToMany(mappedBy = "solicitante")
    private List<SolicitacaoDeAnalise> solicitacoesDeAnalise = new ArrayList<>();
    public Solicitante() {
    }

    public Solicitante(String cnpj, String nomeFantasia, String endereco) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endererco) {
        this.endereco = endererco;
    }

    public List<SolicitacaoDeAnalise> getSolicitacoesDeAnalise() {
        return solicitacoesDeAnalise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solicitante that = (Solicitante) o;

        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return cnpj != null ? cnpj.hashCode() : 0;
    }
}
