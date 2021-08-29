package com.clodoaldo.api.model.test;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.model.TipoCerveja;
import lombok.Builder;
import com.sun.istack.NotNull;


@Builder
public class CervejaTestDTO {

    @Builder.Default
    public Long id = 1L;

    @Builder.Default
    private String nome = "BHAMMA";

    @Builder.Default
    private TipoCerveja tipo = TipoCerveja.LAGER;

    @Builder.Default
    private  int teorAlcool = 16;

    @Builder.Default
    private int quantidade= 10;

    @Builder.Default
    private int quantidadeMaxima = 40;

    public CervejaDTO toCervejaDTO() {
        return new CervejaDTO(
                id,
                nome,
                tipo,
                teorAlcool,
                quantidade,
                quantidadeMaxima);
    }

    @Override
    public String toString() {
        return id + " " +
                nome +" " +
                tipo + " " +
                teorAlcool + " " +
                quantidade + " " +
                quantidadeMaxima  ;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


