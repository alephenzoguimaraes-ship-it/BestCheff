# BestCheff 🍽️

Sistema web de gestão para restaurantes — painel central de controle conectado ao banco de dados das lojas.

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java (JDK) | 21 |
| Jakarta Servlet / JSP | — |
| JSTL | 3.0 |
| Driver JDBC (Firebird) | Jaybird |
| Banco de Dados | Firebird |
| Servidor | Apache Tomcat 11 |
| IDE | Eclipse |

## 📋 Funcionalidades

- Gestão de comandas
- Gerenciamento de produtos
- Controle de vendas
- Emissão de NF-e (Nota Fiscal Eletrônica)
- Painel centralizado multi-loja

## ⚙️ Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Apache Tomcat 11](https://tomcat.apache.org/download-11.cgi)
- [Firebird](https://firebirdsql.org/en/firebird-5-0/) (banco de dados)
- [Eclipse IDE for Enterprise Java](https://www.eclipse.org/downloads/)

## 🚀 Como rodar no Eclipse

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/BestCheff.git
   ```

2. Abra o Eclipse e vá em:
   `File → Import → Existing Projects into Workspace`

3. Selecione a pasta do projeto clonado.

4. Configure o servidor Tomcat 11 em:
   `Window → Preferences → Server → Runtime Environments`

5. Configure a conexão com o banco Firebird no arquivo de propriedades do projeto.

6. Clique com o botão direito no projeto:
   `Run As → Run on Server` e selecione o Tomcat 11.

## 🗄️ Banco de Dados

O sistema utiliza **Firebird** como banco de dados. Certifique-se de que o serviço do Firebird está rodando antes de iniciar a aplicação.

A conexão é feita via **JDBC** usando o driver **Jaybird**.

## 📁 Estrutura do Projeto

```
BestCheff/
├── src/
│   └── main/
│       ├── java/        # Servlets e lógica de negócio
│       └── webapp/      # JSPs, CSS, JS
├── BaseDados/           # Scripts SQL / estrutura do banco
├── .gitignore
└── README.md
```

## 👤 Autor

Desenvolvido por **Aleph**
