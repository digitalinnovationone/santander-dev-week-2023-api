package me.dio.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import me.dio.application.usecase.TransferFundsUseCase;
import me.dio.domain.exception.BusinessException;
import me.dio.domain.exception.NotFoundException;
import me.dio.domain.model.Transaction;
import me.dio.domain.model.TransactionType;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;

public class TransferFundsService implements TransferFundsUseCase {

    private final UserRepository userRepository;

    public TransferFundsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void transfer(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor da transferência deve ser maior que zero.");
        }

        if (sourceAccountNumber.equals(destinationAccountNumber)) {
            throw new BusinessException("A conta de origem e destino não podem ser iguais.");
        }

        // 1. Buscar contas
        User sourceUser = userRepository.findByAccountNumber(sourceAccountNumber)
                .orElseThrow(() -> new NotFoundException("Conta de origem " + sourceAccountNumber + " não encontrada."));

        User destinationUser = userRepository.findByAccountNumber(destinationAccountNumber)
                .orElseThrow(() -> new NotFoundException("Conta de destino " + destinationAccountNumber + " não encontrada."));

        var sourceAccount = sourceUser.getAccount();
        var destinationAccount = destinationUser.getAccount();

        // 2. Validar Limite Diário de Pix
        BigDecimal totalTransferredToday = userRepository.getDailyPixTotal(sourceAccount.getId(), LocalDate.now());
        BigDecimal totalWithCurrentTransfer = totalTransferredToday.add(amount);

        if (totalWithCurrentTransfer.compareTo(sourceAccount.getPixDailyLimit()) > 0) {
            throw new BusinessException("Transferência não permitida. O limite Pix diário é de R$ " 
                    + sourceAccount.getPixDailyLimit() + ". Você já transferiu R$ " + totalTransferredToday 
                    + " hoje. Valor solicitado: R$ " + amount);
        }

        // 3. Realizar movimentação (Regras de Domínio)
        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);

        // 4. Salvar atualizações das contas/usuários
        userRepository.save(sourceUser);
        userRepository.save(destinationUser);

        // 5. Registrar transações no extrato histórico
        Transaction transferOut = new Transaction(
                null,
                TransactionType.TRANSFER_OUT,
                amount,
                LocalDateTime.now(),
                "Pix enviado para a conta " + destinationAccountNumber + " (Agência: " + destinationAccount.getAgency() + ")"
        );
        userRepository.saveTransaction(sourceAccount.getId(), transferOut);

        Transaction transferIn = new Transaction(
                null,
                TransactionType.TRANSFER_IN,
                amount,
                LocalDateTime.now(),
                "Pix recebido da conta " + sourceAccountNumber + " (Agência: " + sourceAccount.getAgency() + ")"
        );
        userRepository.saveTransaction(destinationAccount.getId(), transferIn);
    }
}
