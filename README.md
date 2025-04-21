# Hackathon FIAP “Inovação para otimização de atendimento no SUS (Sistema Único de Saúde)"
## Visão Geral

Descrição funcional: A aplicação ofere ao
paciente mais facilidade e agilidade ao marcar suas consultas e
procedimentos, reduzindo filas e o tempo de espera.

Tecnologias e abordagens:
- Java 17
- Spring Boot
- Spring Data JPA
- Arquitetura Clean Architecture
- Banco de Dados Relacional PostgreSQL

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos atendidos:

- **Java 17**: O projeto utiliza o Java 17. Certifique-se de que sua IDE está configurada com o Java 17.
- **Maven**: Usado para gerenciar dependências e construir o projeto.

## Passos para Configuração

1. **Clone o Repositório:**

   Abra o terminal e clone o repositório usando o comando:

   ```bash
   git clone https://github.com/bpcavalcante/hackathon-pos-fiap-sus-agendamento.git

2. **Acesse a Branch main:**

   Depois de clonar o repositório, navegue até o diretório do projeto e altere para a branch main:

   ```bash
   git checkout main

3. **Importe o Projeto na IDE:**

- Abra sua IDE preferida (por exemplo, IntelliJ IDEA ou Eclipse).
- Certifique-se de que o **Java 17** está configurado como JDK.
- Importe o projeto como um projeto Maven existente.

4. **Construir o Projeto:**

   No terminal, dentro do diretório do projeto, execute o comando Maven para construir o projeto, ou em algumas IDE já constroem automaticamente:

   ```bash
   mvn clean install

5. **Executar o Projeto:**
   Depois de construir o projeto, você pode executá-lo diretamente na IDE ou via linha de comando:
   ```bash
   mvn spring-boot:run

O servidor será iniciado na porta **8080**


6. **Subir o container Docker:**
   Você precisará subir o container com as configurações, que estão no arquivo docker-compose dentro do projeto:
   ```bash
   docker-compose up

O container PostgreSQL será iniciado na porta **5432**


7. **Testando o Sistema:**
   Use os comandos curl abaixo para testar as funcionalidades do sistema:
    - **Modulo Usuarios**
      - **Cadastrar Usuario**
        ```bash
        curl --request POST \
        --url http://localhost:8080/usuarios \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/11.0.2' \
        --data '{
        "nome": "Juliana Ramos",
        "email": "juliana.ramos@empresa.com",
        "telefone": "(41) 99999-0000",
        "dataNascimento": "1990-11-25",
        "cpf": "456.123.789-09"
        }'
      - **Atualizar Usuario**
        ```bash
        curl --request PUT \
        --url http://localhost:8080/usuarios/2 \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/11.0.2' \
        --data '{
        "nome": "Bruno Ramos",
        "email": "juliana.ramos@empresa.com",
        "telefone": "(41) 99999-0000",
        "dataNascimento": "1990-11-25",
        "cpf": "456.123.789-09"
        }'
      - **Buscar Usuario Por ID**
        ```bash
        curl --request GET \
        --url http://localhost:8080/usuarios/2

   - **Modulo Agendamentos**
     - **Cadastrar Agendamento**
       ```bash
        curl --request POST \
        --url http://localhost:8080/agendamentos \
        --header 'Content-Type: application/json' \
        --data '{
        "protocolo": "Urologista",
        "dataAgendamento": "2027-05-20T09:00:00",
        "usuarioId": 2,
        "profissionalId": 7,
        "unidadeSaudeId": 5
        }
        '
       
     - **Cancelar Agendamento**
       ```bash
        curl --request PUT \
        --url http://localhost:8080/agendamentos/cancelar/2
       
     - **Detalhar Agendamento Por ID**
       ```bash
        curl --request GET \
        --url http://localhost:8080/agendamentos/2

     - **Listar Agendamentos por ID Usuario**
       ```bash
        curl --request GET \
        --url http://localhost:8080/agendamentos/usuarios/2

8. **Verificando doc Swagger:**
   Acesse o link http://localhost:8080/swagger-ui/index.html com o projeto rodando
