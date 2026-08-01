package me.dio.infrastructure.config;

import me.dio.application.service.CreateUserService;
import me.dio.application.service.ManageCardService;
import me.dio.application.service.TransferFundsService;
import me.dio.application.usecase.CreateUserUseCase;
import me.dio.application.usecase.ManageCardUseCase;
import me.dio.application.usecase.TransferFundsUseCase;
import me.dio.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserService(userRepository);
    }

    @Bean
    public TransferFundsUseCase transferFundsUseCase(UserRepository userRepository) {
        return new TransferFundsService(userRepository);
    }

    @Bean
    public ManageCardUseCase manageCardUseCase(UserRepository userRepository) {
        return new ManageCardService(userRepository);
    }
}
