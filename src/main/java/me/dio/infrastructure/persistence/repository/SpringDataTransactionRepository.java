package me.dio.infrastructure.persistence.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import me.dio.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM tb_transaction t " +
           "WHERE t.accountId = :accountId AND t.type = :type AND t.timestamp >= :startOfDay")
    BigDecimal sumAmountByAccountIdAndTypeAndTimestampAfter(
            @Param("accountId") Long accountId,
            @Param("type") String type,
            @Param("startOfDay") LocalDateTime startOfDay
    );
}
