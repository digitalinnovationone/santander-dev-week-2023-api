package me.dio.controller.dto;

import java.math.BigDecimal;

import me.dio.domain.model.Account;

public record AccountDto(String number, String agency, BigDecimal balance, BigDecimal limit) {

    public static AccountDto fromModel(Account model) {
        return new AccountDto(model.getNumber(), model.getAgency(), model.getBalance(), model.getLimit());
    }

    public Account toModel() {
        Account model = new Account();
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setBalance(this.balance);
        model.setLimit(this.limit);
        return model;
    }
}
