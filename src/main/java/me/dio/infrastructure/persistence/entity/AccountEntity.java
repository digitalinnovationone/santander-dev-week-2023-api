package me.dio.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "tb_account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String agency;

    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "additional_limit", precision = 13, scale = 2, nullable = false)
    private BigDecimal limit;

    @Column(name = "pix_daily_limit", precision = 13, scale = 2, nullable = false)
    private BigDecimal pixDailyLimit;

    public AccountEntity() {}

    public AccountEntity(Long id, String number, String agency, BigDecimal balance, BigDecimal limit, BigDecimal pixDailyLimit) {
        this.id = id;
        this.number = number;
        this.agency = agency;
        this.balance = balance;
        this.limit = limit;
        this.pixDailyLimit = pixDailyLimit;
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
