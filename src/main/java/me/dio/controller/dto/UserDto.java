package me.dio.controller.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;

import me.dio.domain.model.User;

public record UserDto(
        Long id,
        String name, 
        AccountDto account, 
        List<FeatureDto> features, 
        List<CardDto> cards, 
        List<NewsDto> news) {

    public static UserDto fromModel(User model) {
        var accountDto = AccountDto.fromModel(model.getAccount());
        var featuresDto = model.getFeatures().stream().map(FeatureDto::fromModel).collect(toList());
        var cardsDto = model.getCards().stream().map(CardDto::fromModel).collect(toList());
        var newsDto = model.getNews().stream().map(NewsDto::fromModel).collect(toList());

        return new UserDto(model.getId(), model.getName(), accountDto, featuresDto, cardsDto, newsDto);
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(this.account.toModel());
        model.setFeatures(this.features.stream().map(FeatureDto::toModel).collect(toList()));
        model.setCards(this.cards.stream().map(CardDto::toModel).collect(toList()));
        model.setNews(this.news.stream().map(NewsDto::toModel).collect(toList()));
        return model;
    }
}

