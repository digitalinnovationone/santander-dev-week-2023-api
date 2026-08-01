package me.dio.infrastructure.persistence.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.dio.domain.model.Transaction;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.infrastructure.persistence.entity.TransactionEntity;
import me.dio.infrastructure.persistence.entity.UserEntity;
import me.dio.infrastructure.persistence.mapper.UserMapper;
import me.dio.infrastructure.persistence.repository.SpringDataTransactionRepository;
import me.dio.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryDbAdapter implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    private final SpringDataTransactionRepository springDataTransactionRepository;

    public UserRepositoryDbAdapter(
            SpringDataUserRepository springDataUserRepository,
            SpringDataTransactionRepository springDataTransactionRepository) {
        this.springDataUserRepository = springDataUserRepository;
        this.springDataTransactionRepository = springDataTransactionRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataUserRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByAccountNumber(String number) {
        return springDataUserRepository.findByAccountNumber(number)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByAccountNumber(String number) {
        return springDataUserRepository.existsByAccountNumber(number);
    }

    @Override
    public boolean existsByCardNumber(String number) {
        return springDataUserRepository.existsByCardNumber(number);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity savedEntity = springDataUserRepository.save(entity);
        return UserMapper.toDomain(savedEntity);
    }

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataUserRepository.deleteById(id);
    }

    @Override
    public BigDecimal getDailyPixTotal(Long accountId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        return springDataTransactionRepository.sumAmountByAccountIdAndTypeAndTimestampAfter(
                accountId,
                "TRANSFER_OUT",
                startOfDay
        );
    }

    @Override
    public void saveTransaction(Long accountId, Transaction transaction) {
        TransactionEntity entity = UserMapper.toEntity(accountId, transaction);
        springDataTransactionRepository.save(entity);
    }
}
