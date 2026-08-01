package me.dio.application.usecase;

import java.util.List;
import me.dio.domain.model.User;

public interface CreateUserUseCase {

    User findById(Long id);

    User create(User userToCreate);

    List<User> findAll();

    void delete(Long id);
}
