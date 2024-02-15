# CineDev API

## Descrição
CineDev API é uma solução robusta desenvolvida em Java com Spring Boot. Esta API RESTful
foi criada para proporcionar uma experiência de gerenciamento eficiente tanto para clientes
quanto para administradores, garantindo um ambiente seguro e intuitivo.

## Funcionalidades Principais
### Autenticação em Dois Níveis:
* **Cliente**: Acesso restrito para gerenciamento de conta, visualização de sessões e filmes e realização de reservas.
* **Administrador**: Controle total sobre o sistema, permitindo gerenciar sessões, filmes e usuários.

### Visualização de Sessões:
* Os clientes têm a capacidade de visualizar todas as sessões disponíveis, detalhes sobre os filmes
em exibição e informações relevantes, como horários e salas.

### Reservas Eficientes:
* Os clientes podem realizar reservas de maneira intuitiva, selecionando a sessão desejada, escolhendo
os assentos disponíveis e finalizando o processo de reserva de forma rápida.

### Controle para Administradores:
* Os administradores têm acesso a um painel completo, permitindo a adição, edição de sessões,
gestão de filmes em cartaz, controle de usuários e demais configurações do sistema.

### Segurança:
* Utilização de autenticação baseada em JWT, tem dois níveis de acesso para garantir a segurança dos
dados e a integridade do sistema. As informações dos usuários são protegidas, proporcionando uma
experiência confiável.

## Tecnologias Utilizadas
Desenvolvido em Java com Spring Boot, o sistema é baseado em tecnologias modernas que garantem desempenho,
escalabilidade e manutenibilidade. A arquitetura RESTful proporciona uma comunicação eficiente entre as
diferentes partes do sistema.

## Endpoints
### Autenticação e Cadastro
* POST `/auth/login`,
* POST `/auth/signup`,

### Área Pública
* GET `/public/sessions`,
* GET `/public/sessions/{uuid}`,
* GET `/public/sessions/week`,
* GET `/public/sessions/genres`,
* GET `/public/sessions/films/{uuid}`,
* GET `/public/films`,
* GET `/public/films/{uuid}`,
* GET `/public/films/genres`,

### Usuário
* GET `/users` (ADMIN),
* POST `/users` (ADMIN),
* PUT `/users/{uuid}` (ADMIN),
* POST `/users/{uuid}/enable` (ADMIN),
* POST `/users/{uuid}/disable` (ADMIN),
* GET `/users/{uuid}` (ADMIN, CLIENT),
* PUT `/users/{uuid}/profile` (ADMIN, CLIENT),
* PUT `/users/{uuid}/password` (ADMIN, CLIENT),
* PUT `/users/{uuid}/picture` (ADMIN, CLIENT),

### Filmes
* GET `/films` (ADMIN),
* POST `/films` (ADMIN),
* GET `/films/{uuid}` (ADMIN),
* PUT `/films/{uuid}` (ADMIN),
* DELETE `/films/{uuid}` (ADMIN),

### Salas
* GET `/rooms` (ADMIN),
* GET `/rooms/{uuid}` (ADMIN),
* POST `/rooms` (ADMIN),
* PUT `/rooms/{uuid}` (ADMIN),
* PUT `/rooms/{uuid}/maps` (ADMIN),
* DELETE `/rooms/{uuid}` (ADMIN),
* GET `/rooms/sessions/{uuid}` (ADMIN, CLIENT),

### Mapas
* GET `/maps/rooms/{uuid}` (ADMIN),
* GET `/maps/sessions/{uuid}` (ADMIN, CLIENT),

### Sessões
* GET `/sessions` (ADMIN),
* GET `/sessions/{uuid} (ADMIN)`,
* POST `/sessions` (ADMIN),
* PUT `/sessions/{uuid}` (ADMIN),
* POST `/sessions/{uuid}/close` (ADMIN),
* POST `/sessions/{uuid}/open` (ADMIN),

### Ingressos
* GET `/tickets/sessions/{uuid}` (ADMIN, CLIENT),
* GET `/tickets/reservations/{uuid}` (ADMIN, CLIENT),

### Reservas
* GET `/reservations` (ADMIN),
* GET `/reservations/sessions/{uuid}` (ADMIN, CLIENT),
* GET `/reservations/users/{uuid} (ADMIN, CLIENT)`,
* POST `/reservations` (ADMIN, CLIENT),
* PUT `/reservations/{uuid}/cancel` (ADMIN, CLIENT),

## Contribuição
Se você quiser contribuir para este projeto, siga estas etapas:
1. Faça um fork do repositório.
2. Crie uma nova branch com sua feature: `git checkout -b minha-feature`
3. Faça o commit das mudanças: `git commit -m 'Adicione minha feature'`
4. Envie para o repositório original: `git push origin minha-feature`
5. Crie um pull request.
