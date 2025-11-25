package br.com.zaipraixis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "consultores")
public class Consultor {

    @Id
    private String id;

    private String nome;
    private String telefone;
    private String email;
    private String areaEspecializacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    public Consultor() {}

    public Consultor( String nome, String telefone, String email, String areaEspecializacao, LocalDate dataCriacao) {

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.areaEspecializacao = areaEspecializacao;
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        this.areaEspecializacao = areaEspecializacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Consultor consultor)) return false;
        return Objects.equals(id, consultor.id) && Objects.equals(nome, consultor.nome) && Objects.equals(telefone, consultor.telefone) && Objects.equals(email, consultor.email) && Objects.equals(areaEspecializacao, consultor.areaEspecializacao) && Objects.equals(dataCriacao, consultor.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, email, areaEspecializacao, dataCriacao);
    }
}
