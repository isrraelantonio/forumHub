# Forum Hub
Challeng back end com Spring boot 

**Forum Hub** é um sistema de back-end desenvolvido em **Java** para gerenciamento de fóruns temáticos relacionados a cursos específicos.  
O sistema permite a publicação de tópicos, interação entre usuários e gerenciamento de entidades como **usuários, cursos e perfis**.

---

## 🚀 Funcionalidades
- **Gerenciamento de usuários**
  - Criação de usuários
  - Perfis de usuário com permissões específicas
- **Gerenciamento de cursos**
  - Cadastro e listagem de cursos
  - Associação de tópicos a cursos
- **Fórum de tópicos**
  - Criação e publicação de tópicos relacionados a cursos
  - Registro automático de autor e data de publicação
  - Interação com respostas em tópicos
- **Autenticação e segurança**
  - Autenticação stateless via **Spring Security**
  - Geração e validação de tokens (**JWT**)
  - Controle de permissões por perfil

---

## 🛠️ Tecnologias Utilizadas
- **Java**
- **Maven** – gerenciamento de dependências
- **Spring Boot** – estrutura principal do projeto
- **Spring Security** – segurança, permissões de acesso e autenticação JWT
- **Lombok** – para reduzir verbosidade no código
- **MySQL** – banco de dados relacional
- **Insomnia** – testes de endpoints da API

---

## Pré-requisitos para executar o Projeto

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://dev.mysql.com/downloads/)

