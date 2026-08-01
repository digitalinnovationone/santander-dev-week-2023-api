package me.dio.application.usecase;

import java.math.BigDecimal;

public interface TransferFundsUseCase {

    void transfer(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount);
}
