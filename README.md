# 🚀 Pipeline de Deploy Automático — Projeto de Extensão

Este repositório contém a pipeline completa para realizar o **deploy automatizado** da aplicação. Todo o fluxo — desde testes, build, criação da imagem Docker até a publicação e execução nos servidores — é executado automaticamente através de integração e entrega contínuas.

---

## 📌 O que a Pipeline Faz?

A pipeline foi configurada para executar todas as etapas necessárias para garantir um deploy confiável, seguro e totalmente automatizado. O processo funciona da seguinte forma:

### ✅ 1. Verificação dos Testes
A pipeline executa todos os testes da aplicação antes de qualquer build, garantindo que apenas código funcional siga para as próximas etapas.

### 🏗️ 2. Build da Aplicação
Após passar pelos testes, a aplicação é compilada e empacotada.

### 🐳 3. Criação da Imagem Docker
Com o artefato gerado, a pipeline cria automaticamente a imagem Docker da aplicação.

### 📤 4. Envio da Imagem para o Docker Hub
A imagem é publicada no Docker Hub, permitindo que qualquer servidor a acesse para execução.

### 🌐 5. Acesso à Máquina Pública
A pipeline realiza conexão via SSH com a máquina pública, preparando o ambiente inicial de deploy.

### 🔐 6. Acesso à Máquina Privada
Após isso, a pipeline acessa a máquina privada onde a aplicação será executada.

### ▶️ 7. Execução do Docker Compose
Na máquina privada, o `docker compose` configurado no servidor é executado para:

- baixar a versão atualizada da imagem do Docker Hub,
- subir ou atualizar os containers,
- disponibilizar automaticamente a nova versão da aplicação.

Tudo isso ocorre sem intervenção manual.

---

## 🔧 Tecnologias e Ferramentas Utilizadas

- **GitHub Actions** — Automação da pipeline
- **Docker & Docker Hub** — Conteinerização e distribuição
- **SSH** — Acesso remoto e execução de comandos
- **Docker Compose** — Orquestração dos serviços em produção

---

## 🔄 Fluxo Completo de Deploy

A cada push na branch principal ou merge de Pull Request:

1. Testes são validados.
2. A aplicação é compilada.
3. A imagem Docker é criada.
4. A imagem é enviada ao Docker Hub.
5. A pipeline acessa a máquina pública.
6. A pipeline acessa a máquina privada.
7. O Docker Compose é executado.

A nova versão da aplicação entra no ar automaticamente.

---

## 🤝 Contribuição

Contribuições são muito bem-vindas! Para colaborar:

1. Faça um fork do repositório
2. Crie uma nova branch (`git checkout -b feature/NovaFeature`)
3. Faça suas alterações
4. Commit (`git commit -m "Descrição da melhoria"`)
5. Push para a branch (`git push origin feature/NovaFeature`)
6. Abra um Pull Request

---


