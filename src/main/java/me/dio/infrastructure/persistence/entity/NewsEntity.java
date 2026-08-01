package me.dio.infrastructure.persistence.entity;

import jakarta.persistence.Entity;

@Entity(name = "tb_news")
public class NewsEntity extends BaseItemEntity {

    public NewsEntity() {}

    public NewsEntity(Long id, String icon, String description) {
        super(id, icon, description);
    }
}
