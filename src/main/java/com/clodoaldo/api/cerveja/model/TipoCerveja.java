package com.clodoaldo.api.cerveja.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCerveja {

    PILSEN("Pilsen"),
    LAGER("Lager"),
    PALE("Pale "),
    BOCK("Bock "),
    WEISSBIER("Weissbier ");

    private final String description;
}





