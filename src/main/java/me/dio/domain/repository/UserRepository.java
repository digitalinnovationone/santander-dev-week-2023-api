package me.dio.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import me.dio.domain.model.User;
import me.dio.domain.model.Transaction;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByAccountNumber(String number);

    boolean existsByAccountNumber(String number);

    boolean existsByCardNumber(String number);

    User save(User user);

    List<User> findAll();

    void deleteById(Long id);

    // Método para obter o total de transações Pix enviadas no dia de hoje
    BigDecimal getDailyPixTotal(Long accountId, LocalDate date);

    // Método para registrar uma transação financeira associada à conta
    void saveTransaction(Long accountId, Transaction transaction);
}
