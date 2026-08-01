package me.dio.infrastructure.persistence.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import me.dio.domain.model.Account;
import me.dio.domain.model.Card;
import me.dio.domain.model.Feature;
import me.dio.domain.model.News;
import me.dio.domain.model.Transaction;
import me.dio.domain.model.TransactionType;
import me.dio.domain.model.User;
import me.dio.infrastructure.persistence.entity.AccountEntity;
import me.dio.infrastructure.persistence.entity.CardEntity;
import me.dio.infrastructure.persistence.entity.FeatureEntity;
import me.dio.infrastructure.persistence.entity.NewsEntity;
import me.dio.infrastructure.persistence.entity.TransactionEntity;
import me.dio.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    // ==========================================
    // DOMAIN -> JPA ENTITY
    // ==========================================

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return new UserEntity(
                domain.getId(),
                domain.getName(),
                toEntity(domain.getAccount()),
                toEntity(domain.getCard()),
                toFeatureEntities(domain.getFeatures()),
                toNewsEntities(domain.getNews())
        );
    }

    public static AccountEntity toEntity(Account domain) {
        if (domain == null) return null;
        return new AccountEntity(
                domain.getId(),
                domain.getNumber(),
                domain.getAgency(),
                domain.getBalance(),
                domain.getLimit(),
                domain.getPixDailyLimit()
        );
    }

    public static CardEntity toEntity(Card domain) {
        if (domain == null) return null;
        return new CardEntity(
                domain.getId(),
                domain.getNumber(),
                domain.getLimit(),
                domain.getMaxLimit(),
                domain.isActive()
        );
    }

    public static FeatureEntity toEntity(Feature domain) {
        if (domain == null) return null;
        return new FeatureEntity(
                domain.getId(),
                domain.getIcon(),
                domain.getDescription()
        );
    }

    public static NewsEntity toEntity(News domain) {
        if (domain == null) return null;
        return new NewsEntity(
                domain.getId(),
                domain.getIcon(),
                domain.getDescription()
        );
    }

    public static TransactionEntity toEntity(Long accountId, Transaction domain) {
        if (domain == null) return null;
        return new TransactionEntity(
                domain.getId(),
                accountId,
                domain.getType().name(),
                domain.getAmount(),
                domain.getTimestamp(),
                domain.getDescription()
        );
    }

    // ==========================================
    // JPA ENTITY -> DOMAIN
    // ==========================================

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getName(),
                toDomain(entity.getAccount()),
                toDomain(entity.getCard()),
                toFeaturesDomain(entity.getFeatures()),
                toNewsDomain(entity.getNews())
        );
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) return null;
        return new Account(
                entity.getId(),
                entity.getNumber(),
                entity.getAgency(),
                entity.getBalance(),
                entity.getLimit(),
                entity.getPixDailyLimit()
        );
    }

    public static Card toDomain(CardEntity entity) {
        if (entity == null) return null;
        return new Card(
                entity.getId(),
                entity.getNumber(),
                entity.getLimit(),
                entity.getMaxLimit(),
                entity.isActive()
        );
    }

    public static Feature toDomain(FeatureEntity entity) {
        if (entity == null) return null;
        Feature domain = new Feature();
        domain.setId(entity.getId());
        domain.setIcon(entity.getIcon());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static News toDomain(NewsEntity entity) {
        if (entity == null) return null;
        News domain = new News();
        domain.setId(entity.getId());
        domain.setIcon(entity.getIcon());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) return null;
        return new Transaction(
                entity.getId(),
                TransactionType.valueOf(entity.getType()),
                entity.getAmount(),
                entity.getTimestamp(),
                entity.getDescription()
        );
    }

    // ==========================================
    // LIST CONVERSIONS
    // ==========================================

    private static List<FeatureEntity> toFeatureEntities(List<Feature> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }

    private static List<NewsEntity> toNewsEntities(List<News> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }

    private static List<Feature> toFeaturesDomain(List<FeatureEntity> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(UserMapper::toDomain).collect(Collectors.toList());
    }

    private static List<News> toNewsDomain(List<NewsEntity> list) {
        if (list == null) return Collections.emptyList();
        return list.stream().map(UserMapper::toDomain).collect(Collectors.toList());
    }
}
