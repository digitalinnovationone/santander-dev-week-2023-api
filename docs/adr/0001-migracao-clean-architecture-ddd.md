# ADR 0001: Migração para Clean Architecture, DDD e Java 21

## Status
Proposta (Proposed)

## Contexto
O projeto original (Claro Dev Week 2026 API) foi concebido em uma arquitetura clássica MVC e Spring Boot 3.1.x, onde as entidades de banco de dados (JPA) são acopladas diretamente à camada de negócio, e os controladores interagem diretamente com repositórios e serviços anotados pelo framework.

Para evoluir o projeto e demonstrar práticas recomendadas para sistemas corporativos complexos, queremos:
1. Desacoplar totalmente as regras de negócio de frameworks e bibliotecas externas (usando Java Puro).
2. Adotar os princípios de **Domain-Driven Design (DDD)** para modelar e proteger a consistência do domínio.
3. Atualizar a pilha tecnológica para usar **Java 21** e **Spring Boot 3.3.x / 3.4.x**.
4. Resolver erros de compilação da IDE e do Gradle associados à mudança de versão do JDK.
5. Adicionar novas regras de negócio no domínio bancário para fins de demonstração (Pix com limite diário, histórico de transações e controle de limites de cartão).

## Decisão
Decidimos refatorar o projeto de acordo com os princípios da **Clean Architecture** (Arquitetura Limpa), dividindo o código em quatro camadas principais sob o pacote base `me.dio`:

1. **Domain (Domínio):**
   - **Pergunta que responde:** Quais são as regras e contratos do negócio?
   - **Descrição:** Implementada em Java puro, sem frameworks. Contém as Entidades de Domínio puras (como `User`, `Account`, `Card`, `Transaction`), Objetos de Valor (Value Objects), regras de validação interna e contratos/portas de saída (interfaces de Repositórios/Gateways).
   
2. **Application (Aplicação):**
   - **Pergunta que responde:** Qual caso de uso devemos executar?
   - **Descrição:** Implementada em Java puro, sem frameworks. Contém os Casos de Uso (como `TransferFundsUseCase` e `ManageCardUseCase`) que orquestram a execução dos fluxos do negócio, interagindo com as portas definidas no Domínio.

3. **Presentation (Apresentação):**
   - **Pergunta que responde:** Como o usuário/sistema externo conversa conosco?
   - **Descrição:** Camada que utiliza o Spring Web MVC. Contém controladores REST (como `UserController`), DTOs de entrada e saída, e classes de mapeamento de requisições e respostas.

4. **Infrastructure (Infraestrutura):**
   - **Pergunta que responde:** Como conversamos com tecnologias externas?
   - **Descrição:** Contém os detalhes técnicos do projeto: persistência de dados (Entidades JPA, repositórios do Spring Data JPA), segurança (Spring Security), comunicação HTTP externa (OpenFeign) e a fiação do framework (como a classe `@Configuration` para injeção manual das dependências das camadas de negócio puros).

Também decidimos atualizar a versão mínima do Java para a **LTS 21** no `build.gradle`, bem como as dependências do Spring Boot para suporte nativo e melhorias de desempenho.

## Consequências
- **Positivas:**
  - **Testabilidade:** As regras de negócio do core podem ser testadas unitariamente de forma extremamente rápida, sem a necessidade de levantar o contexto do Spring ou bancos de dados reais.
  - **Manutenibilidade:** O negócio está protegido contra mudanças em frameworks de persistência ou de transporte web.
  - **Robustez:** Regras de negócio como limites diários de Pix e consistência do saldo bancário são validadas rigidamente no domínio puro Java.
- **Negativas:**
  - **Complexidade:** Há um aumento no número de classes e arquivos devido à separação entre Entidades de Domínio e Entidades JPA, necessitando de conversores de mapeamento (`Mappers`).
  - **Curva de Aprendizado:** Desenvolvedores acostumados ao MVC clássico do Spring Boot precisarão entender o fluxo de controle e injeção de dependências via Beans de configuração manual.

## Regras de Negócio Propostas a Implementar
1. **Transferência Pix:** O caso de uso valida se o usuário possui saldo e se o valor da transferência não excede o limite diário configurado para a conta do usuário.
2. **Histórico de Transações (Extrato):** Todas as mutações financeiras (depósito, saque, transferências de entrada/saída) geram uma entidade de `Transaction` imutável persistida no banco.
3. **Cartão de Crédito:** O ajuste de limite só é permitido para cartões ativos e até o valor máximo aprovado na política de crédito definida no domínio.
