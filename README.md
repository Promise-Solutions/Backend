# 📌 Backoffice para Gerenciamento de Projetos e Atendimentos

Este projeto é um backoffice com o objetivo de auxiliar empresas no gerenciamento de seus projetos, atendimentos e controle financeiro. Ele oferece funcionalidades para organizar projetos, seus respectivos serviços, e acompanhar as finanças da empresa.

## 🚀 Funcionalidades Principais

* **Gerenciamento de Projetos:**
    * Criação e edição de projetos.
    * Associação de serviços a cada projeto.
* **Gerenciamento de Atendimentos:**
    * Registro e acompanhamento de atendimentos aos clientes.
    * Associação de atendimentos a projetos.
* **Controle Financeiro:**
    * Registro de receitas e despesas.
    * Geração de relatórios financeiros.

## 🔧 Tecnologias Utilizadas

* Java 21
* Spring Boot
* MySQL
* Spring Data JPA
* Swagger

## ⚙️ Como Usar

* Os Endpoints da API podem ser consultados através do swagger.

### Variáveis de Ambiente
Para configurar, você pode definir as seguintes variáveis de ambiente criando um arquivo `.env` na raiz do projeto:

```bash
SERVER_PORT=<port value>

DB_URL=<url value>
DB_USER=<user value>
DB_PASSWORD=<password value>
DB_DRIVER=<driver value>
DB_PLATFORM=<platform value>

LOCALHOST=<localhost value>

JWT_SECRET=<secret value>
```

## ✍️ Contribuição

Contribuições são bem-vindas! Se você deseja contribuir com este projeto, siga estas etapas:

1.  Faça um fork do repositório.
2.  Crie uma branch para a sua feature (`git checkout -b feature/NovaFeature`).
3.  Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`).
4.  Faça push para a branch (`git push origin feature/NovaFeature`).
5.  Crie um Pull Request.