package me.dio.domain.model;

import java.math.BigDecimal;
import me.dio.domain.exception.BusinessException;

public class Account {

    private Long id;
    private String number;
    private String agency;
    private BigDecimal balance;
    private BigDecimal limit; // Limite adicional/cheque especial
    private BigDecimal pixDailyLimit; // Limite diário de transação Pix

    public Account() {
        this.balance = BigDecimal.ZERO;
        this.limit = BigDecimal.ZERO;
        this.pixDailyLimit = new BigDecimal("1000.00"); // Limite diário Pix padrão de R$ 1000,00
    }

    public Account(Long id, String number, String agency, BigDecimal balance, BigDecimal limit, BigDecimal pixDailyLimit) {
        this.id = id;
        this.number = number;
        this.agency = agency;
        this.balance = balance != null ? balance : BigDecimal.ZERO;
        this.limit = limit != null ? limit : BigDecimal.ZERO;
        this.pixDailyLimit = pixDailyLimit != null ? pixDailyLimit : new BigDecimal("1000.00");
    }

    // Regra de Negócio: Depósito
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor do depósito deve ser maior que zero.");
        }
        this.balance = this.balance.add(amount);
    }

    // Regra de Negócio: Saque / Débito
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor do saque deve ser maior que zero.");
        }
        BigDecimal availableBalance = this.balance.add(this.limit);
        if (amount.compareTo(availableBalance) > 0) {
            throw new BusinessException("Saldo insuficiente. Saldo disponível (com limite): R$ " + availableBalance);
        }
        this.balance = this.balance.subtract(amount);
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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getPixDailyLimit() {
        return pixDailyLimit;
    }

    public void setPixDailyLimit(BigDecimal pixDailyLimit) {
        this.pixDailyLimit = pixDailyLimit;
    }
}
