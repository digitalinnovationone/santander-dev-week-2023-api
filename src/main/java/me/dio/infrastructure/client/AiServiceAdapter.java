package me.dio.infrastructure.client;

import me.dio.domain.repository.AiGateway;
import org.springframework.stereotype.Component;

@Component
public class AiServiceAdapter implements AiGateway {

    private final AiFeignClient aiFeignClient;

    public AiServiceAdapter(AiFeignClient aiFeignClient) {
        this.aiFeignClient = aiFeignClient;
    }

    @Override
    public String generateNewsInsight(String userName) {
        try {
            var response = aiFeignClient.getAdvice();
            if (response != null && response.getSlip() != null) {
                return "Olá, " + userName + "! Dica da Claro Dev Week: " + response.getSlip().getAdvice();
            }
        } catch (Exception e) {
            // Fallback em caso de indisponibilidade da API externa
            return "Olá, " + userName + "! Mantenha a constância nos seus estudos de tecnologia e continue inovando!";
        }
        return "Olá, " + userName + "! Mantenha o foco no seu planejamento financeiro.";
    }
}
