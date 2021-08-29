package com.clodoaldo.api.cerveja.DTO;
import com.clodoaldo.api.cerveja.model.TipoCerveja;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CervejaDTO {

    @NotNull
    private  long    iD;
    @NotNull
    private  String  nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCerveja tipo;
    @NotNull
    private  int teorAlcool;
    @NotNull
    private  int quantidade;
    @NotNull
    private  int quantidadeMaxima;


    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCerveja getTipo() {
        return tipo;
    }

    public void setTipo(TipoCerveja tipo) {
        this.tipo = tipo;
    }

    public int getTeorAlcool() {
        return teorAlcool;
    }

    public void setTeorAlcool(int teorAlcool) {
        this.teorAlcool = teorAlcool;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }
}
