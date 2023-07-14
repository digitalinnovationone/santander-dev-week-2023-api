package me.dio.controller.dto;

import me.dio.domain.model.Card;

public record CardDto(String number) {
    
    public static CardDto fromModel(Card model) {
        return new CardDto(model.getNumber());
    }

    public Card toModel() {
        Card card = new Card();
        card.setNumber(this.number);
        return card;
    }
}
