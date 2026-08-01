package me.dio.infrastructure.persistence.repository;

import java.util.Optional;
import me.dio.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM tb_user u WHERE u.account.number = :number")
    Optional<UserEntity> findByAccountNumber(@Param("number") String number);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM tb_user u WHERE u.account.number = :number")
    boolean existsByAccountNumber(@Param("number") String number);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM tb_user u WHERE u.card.number = :number")
    boolean existsByCardNumber(@Param("number") String number);
}
