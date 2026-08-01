package me.dio.domain.repository;

public interface AiGateway {

    // Gera um conselho ou insight personalizado de IA para o usuário
    String generateNewsInsight(String userName);
}
