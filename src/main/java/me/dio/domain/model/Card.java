package me.dio.domain.model;

import java.math.BigDecimal;
import me.dio.domain.exception.BusinessException;

public class Card {

    private Long id;
    private String number;
    private BigDecimal limit;
    private BigDecimal maxLimit;
    private boolean active;

    public Card() {
        this.active = true; // Por padrão o cartão inicia ativo
    }

    public Card(Long id, String number, BigDecimal limit, BigDecimal maxLimit, boolean active) {
        this.id = id;
        this.number = number;
        this.limit = limit;
        this.maxLimit = maxLimit;
        this.active = active;
    }

    // Regra de Negócio: Bloquear cartão
    public void block() {
        this.active = false;
    }

    // Regra de Negócio: Desbloquear cartão
    public void unblock() {
        this.active = true;
    }

    // Regra de Negócio: Alterar limite
    public void updateLimit(BigDecimal newLimit) {
        if (!this.active) {
            throw new BusinessException("Não é possível alterar o limite de um cartão bloqueado.");
        }
        if (newLimit == null || newLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O limite do cartão não pode ser nulo ou negativo.");
        }
        if (this.maxLimit != null && newLimit.compareTo(this.maxLimit) > 0) {
            throw new BusinessException("O novo limite excede o limite máximo aprovado de R$ " + this.maxLimit);
        }
        this.limit = newLimit;
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
