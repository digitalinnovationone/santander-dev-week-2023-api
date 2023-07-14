package me.dio.controller.dto;

import me.dio.domain.model.News;

public record NewsDto(String icon, String description) {

    public static NewsDto fromModel(News model) {
        return new NewsDto(model.getIcon(), model.getDescription());
    }

    public News toModel() {
        News model = new News();
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}

