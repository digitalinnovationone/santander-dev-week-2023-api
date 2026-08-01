package me.dio.application.service;

import java.math.BigDecimal;
import me.dio.application.usecase.ManageCardUseCase;
import me.dio.domain.exception.BusinessException;
import me.dio.domain.exception.NotFoundException;
import me.dio.domain.model.Card;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;

public class ManageCardService implements ManageCardUseCase {

    private final UserRepository userRepository;

    public ManageCardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Card blockCard(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + userId + " não encontrado."));

        Card card = user.getCard();
        if (card == null) {
            throw new BusinessException("O usuário não possui um cartão de crédito associado.");
        }

        card.block();
        userRepository.save(user);
        return card;
    }

    @Override
    public Card unblockCard(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + userId + " não encontrado."));

        Card card = user.getCard();
        if (card == null) {
            throw new BusinessException("O usuário não possui um cartão de crédito associado.");
        }

        card.unblock();
        userRepository.save(user);
        return card;
    }

    @Override
    public Card updateCardLimit(Long userId, BigDecimal newLimit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + userId + " não encontrado."));

        Card card = user.getCard();
        if (card == null) {
            throw new BusinessException("O usuário não possui um cartão de crédito associado.");
        }

        card.updateLimit(newLimit);
        userRepository.save(user);
        return card;
    }
}
