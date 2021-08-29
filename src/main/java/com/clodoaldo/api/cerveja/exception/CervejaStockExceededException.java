package com.clodoaldo.api.cerveja.exception;


public class CervejaStockExceededException extends Exception  {

    public CervejaStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Beers with %s ID to increment informed exceeds " +
                "the max stock capacity: %s", id, quantityToIncrement));
    }
}
