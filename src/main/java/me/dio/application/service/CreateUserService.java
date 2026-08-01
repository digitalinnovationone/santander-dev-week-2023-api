package me.dio.application.service;

import java.util.List;
import me.dio.application.usecase.CreateUserUseCase;
import me.dio.domain.exception.BusinessException;
import me.dio.domain.exception.NotFoundException;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + id + " não encontrado."));
    }

    @Override
    public User create(User userToCreate) {
        if (userToCreate.getAccount() == null) {
            throw new BusinessException("A conta do usuário não pode ser nula.");
        }
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new BusinessException("Este número de conta já existe.");
        }
        if (userToCreate.getCard() != null && userRepository.existsByCardNumber(userToCreate.getCard().getNumber())) {
            throw new BusinessException("Este número de cartão já existe.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + id + " não encontrado."));
        userRepository.deleteById(id);
    }
}
