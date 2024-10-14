
# Microsserviços com Spring Boot e Docker Compose

Este projeto consiste em microsserviços desenvolvidos com Spring Boot, utilizando o Docker Compose para orquestração dos containers. Os principais serviços incluídos são o `user-service` e o `email-service`, além dos serviços de suporte como `config-server` e `eureka-server`.

## Pré-requisitos

Antes de iniciar, certifique-se de ter as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Criaçao do repositório com as configurações dos serviços `user-service` e `email-service` 

### Configuração do `.env`

Você precisará configurar um arquivo `.env` com as variáveis de ambiente necessárias para os serviços. Como referência, utilize o arquivo `.env.example` fornecido no projeto.

```bash
cp .env.example .env
```

Edite o arquivo `.env` com as informações corretas, como `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB`, `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`, entre outras.

### Repositórios de Configuração

Este projeto depende das configurações externas armazenadas no Git. Certifique-se de ter acesso aos repositórios de configuração:

- [ `Exemplo de user-service.properties`]

#### Postgres Database Config
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#### RabbitMQ Configs
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=
spring.rabbitmq.password=

#### Nome da fila para envio de mensagens
spring.rabbitmq.template.default-receive-queue=

#### JWT Pass
security.token.secret=

- [`Exemplo de email-service.properties`]

#### MailTrap Configuration
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

#### RabbitMq Config
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=
spring.rabbitmq.password=

#### Nome da fila para escuta das mensagens
spring.rabbitmq.listener.simple.queue.name=


Complete as configurações com as credenciais corretas.

## Executando os Ambientes

### 1. Ambiente de Desenvolvimento

Para rodar o ambiente de desenvolvimento, execute:

```bash
docker-compose up --build
```

Isso irá iniciar todos os serviços com as configurações padrão.

### 2. Ambiente de Debug

Para rodar o ambiente com suporte a debug, execute:

```bash
docker-compose -f docker-compose.yml -f docker-compose.debug.yml up --build
```

Esse comando adiciona configurações específicas de debug aos microsserviços.

### 3. Ambiente de Testes

Para rodar o ambiente de testes, execute:

```bash
docker-compose -f docker-compose.yml -f docker-compose.test.yml up --build
```

Esse ambiente executa os testes automáticos definidos nos serviços.

## Estrutura do Projeto

- **user-db**: Container com o PostgreSQL, usado pelo `user-service`.
- **eureka-server**: Registro de serviços para descoberta e balanceamento de carga.
- **config-server**: Servidor de configurações centralizadas para os microsserviços.
- **user-service**: Serviço responsável por gerenciar usuários.
- **email-service**: Serviço responsável por gerenciar e-mails.

## Observações

- Certifique-se de que os repositórios de configuração estejam atualizados com os arquivos necessários para cada serviço.
- Os comandos acima assumem que o `.env` está corretamente configurado com as variáveis necessárias para cada serviço.
