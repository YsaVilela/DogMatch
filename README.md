# DogMatch

DogMatch é um aplicativo de relacionamento entre cães, desenvolvido em Flutter para a interface do usuário e com um backend em Java conectado a um banco de dados PostgreSQL. Ele permite que os donos de cães encontrem parceiros caninos ideais para passeios, brincadeiras e interações sociais.

## Funcionalidades Principais

- **Registro de Cães**: Os usuários podem cadastrar seus cães no aplicativo, incluindo informações como raça, idade, temperamento e preferências de interação.

- **Matchmaking**: Com base nas características dos cães registrados, o aplicativo faz sugestões de potenciais "matches" entre os cães, considerando afinidades de comportamento e preferências.

- **Agendamento de Encontros**: Os donos podem agendar encontros entre seus cães, definindo local, data e horário.

- **Chat**: O aplicativo oferece um recurso de bate-papo para que os donos possam se comunicar e combinar os detalhes dos encontros.

## Tecnologias Utilizadas

- **Frontend (Flutter)**:
  - Desenvolvido em Flutter, oferecendo uma interface de usuário moderna e responsiva para dispositivos móveis.
  - Utiliza widgets e componentes nativos do Flutter para proporcionar uma experiência fluida aos usuários.
  
- **Backend (Java)**:
  - Implementado em Java, o backend gerencia as lógicas de negócio, como a lógica de matchmaking e o agendamento de encontros.
  - Comunica-se com o banco de dados PostgreSQL para armazenar e recuperar os dados dos cães e dos usuários.

- **Banco de Dados (PostgreSQL)**:
  - O PostgreSQL é utilizado para armazenar os perfis dos cães e dos usuários, bem como os dados relacionados aos matches e agendamentos de encontros.

## Como Executar o Projeto Localmente

Para executar o projeto localmente, siga estas etapas:

1. **Configuração do Backend**:
   - Certifique-se de ter o Java instalado em sua máquina.
   - Clone o repositório do backend e configure-o conforme as instruções fornecidas no README.

2. **Configuração do Banco de Dados**:
   - Instale o PostgreSQL em sua máquina, se ainda não o tiver.
   - Execute os scripts SQL fornecidos para criar o banco de dados e as tabelas necessárias.

3. **Configuração do Frontend**:
   - Certifique-se de ter o Flutter instalado em seu ambiente de desenvolvimento.
   - Clone o repositório do frontend e siga as instruções para instalar as dependências.

4. **Executando a Aplicação**:
   - Inicie o backend para que ele esteja pronto para receber solicitações do frontend.
   - Inicie o frontend no ambiente de desenvolvimento do Flutter.
   - Abra o aplicativo em um emulador ou dispositivo físico para visualizar e interagir com o DogMatch.

## Contribuição

Contribuições para o projeto são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias, correções de bugs ou novas funcionalidades.

---

Com o DogMatch, encontrar companheiros caninos para seus cães nunca foi tão fácil e divertido! Se você ama cães e tecnologia, este é o aplicativo perfeito para você.
