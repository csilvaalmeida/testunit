package com.clodoaldo.api.cerveja.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadeDTO {

    @NotNull
    @Max(100)
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}


