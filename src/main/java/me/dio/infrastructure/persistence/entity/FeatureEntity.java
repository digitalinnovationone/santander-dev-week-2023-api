package me.dio.infrastructure.persistence.entity;

import jakarta.persistence.Entity;

@Entity(name = "tb_feature")
public class FeatureEntity extends BaseItemEntity {

    public FeatureEntity() {}

    public FeatureEntity(Long id, String icon, String description) {
        super(id, icon, description);
    }
}
