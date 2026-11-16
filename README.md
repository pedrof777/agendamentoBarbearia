#  Agendamento Barbearia

Sistema de agendamento para barbearia, com regras de negócio validadas por testes automatizados — evita conflitos de horário e garante integridade dos dados a cada mudança de código.

##  Sobre o projeto

- Sistema web para agendamento de barbearia, permitindo o gerenciamento de clientes, horários e serviços.
- Desenvolvido utilizando Jakarta EE e empacotado no formato WAR para implantação em servidor de aplicação.
- Gerenciamento de dependências feito com Maven.
- Interface web construída com JSF (MyFaces) utilizando componentes visuais do PrimeFaces e recursos adicionais do OmniFaces.
- Persistência de dados implementada com JPA/Hibernate, utilizando banco de dados relacional PostgreSQL.
- Uso de CDI (Weld) para injeção de dependências e gerenciamento de beans.
- Servidor de aplicação Tomcat.
- Projeto estruturado em arquitetura de camadas, separando responsabilidades em Controller, Service e DAO, utilizando interfaces para melhor organização e manutenção.
- Testes automatizados implementados com JUnit 5 e Mockito, garantindo validação das regras de negócio e isolamento de dependências.


##  Tecnologias

Jakarta EE · JSF (MyFaces) · PrimeFaces · OmniFaces · CDI (Weld) · JPA/Hibernate · PostgreSQL · Tomcat · Maven · JUnit 5 · Mockito

##  Como rodar

### Pré-requisitos

- Java 17+ (ou a versão que seu projeto usa)
- Servidor de aplicação Tomcat
- PostgreSQL
- Maven

### Passos

```bash
git clone https://github.com/pedrof777/agendamento-barbearia
cd agendamento-barbearia
mvn clean install
```

Depois, faz o deploy do arquivo `.war` gerado (em `target/`) no seu servidor Tomcat, e configura a conexão com o PostgreSQL conforme o arquivo de persistência do projeto (`persistence.xml`).

Aplicação disponível em `http://localhost:8080/agendamento-barbearia` (ajuste conforme a porta/contexto configurados no seu Tomcat).
