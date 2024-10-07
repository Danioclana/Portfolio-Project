
# Microsserviços com Spring Boot e Docker Compose

Este projeto consiste em microsserviços desenvolvidos com Spring Boot, utilizando o Docker Compose para orquestração dos containers. Os principais serviços incluídos são o `user-service` e o `email-service`, além dos serviços de suporte como `config-server` e `eureka-server`.

## Pré-requisitos

Antes de iniciar, certifique-se de ter as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Acesso ao repositório com as configurações dos serviços `user-service` e `email-service` (consultar os links abaixo)

### Configuração do `.env`

Você precisará configurar um arquivo `.env` com as variáveis de ambiente necessárias para os serviços. Como referência, utilize o arquivo `.env.example` fornecido no projeto.

```bash
cp .env.example .env
```

Edite o arquivo `.env` com as informações corretas, como `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB`, `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`, entre outras.

### Repositórios de Configuração

Este projeto depende das configurações externas armazenadas no Git. Certifique-se de ter acesso aos repositórios de configuração:

- [Configurações do `user-service`](https://github.com/SeuUsuario/ConfigUserService)
- [Configurações do `email-service`](https://github.com/SeuUsuario/ConfigEmailService)

### Clonar Repositórios de Configuração

Você precisa clonar os repositórios de configuração antes de iniciar os serviços:

```bash
# Clonar o repositório de configurações para o user-service
git clone https://github.com/SeuUsuario/ConfigUserService.git user-service-config

# Clonar o repositório de configurações para o email-service
git clone https://github.com/SeuUsuario/ConfigEmailService.git email-service-config
```

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
