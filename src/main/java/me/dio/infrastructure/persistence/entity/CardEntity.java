package me.dio.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "tb_card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(name = "available_limit", precision = 13, scale = 2, nullable = false)
    private BigDecimal limit;

    @Column(name = "max_limit", precision = 13, scale = 2)
    private BigDecimal maxLimit;

    @Column(nullable = false)
    private boolean active;

    public CardEntity() {}

    public CardEntity(Long id, String number, BigDecimal limit, BigDecimal maxLimit, boolean active) {
        this.id = id;
        this.number = number;
        this.limit = limit;
        this.maxLimit = maxLimit;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
