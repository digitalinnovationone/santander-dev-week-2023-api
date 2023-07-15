package me.dio.controller.dto;

import java.math.BigDecimal;

import me.dio.domain.model.Card;

public record CardDto(String number, BigDecimal limit) {
    
    public static CardDto fromModel(Card model) {
        return new CardDto(model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card card = new Card();
        card.setNumber(this.number);
        card.setLimit(this.limit);
        return card;
    }
}
