package me.dio.application.usecase;

import java.math.BigDecimal;
import me.dio.domain.model.Card;

public interface ManageCardUseCase {

    Card blockCard(Long userId);

    Card unblockCard(Long userId);

    Card updateCardLimit(Long userId, BigDecimal newLimit);
}
