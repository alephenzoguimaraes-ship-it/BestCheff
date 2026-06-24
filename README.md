# BestCheff 🍽️

A web system designed to streamline order (comanda) management, intended to be used by waitstaff. The system simplifies the cashier's workflow, who only needs to settle the bill at checkout.

## 🛠️ Technologies

| Technology | Version |
|---|---|
| Java (JDK) | 21 |
| Jakarta Servlet / JSP | 11 |
| JDBC Driver (Firebird) | Jaybird |
| Database | Firebird |
| Server | Apache Tomcat 11 |
| IDE | Eclipse |

## 📋 Features

- Order (comanda) management
- Product management
- Order tracking

## ⚙️ Prerequisites

Before running the project, make sure you have the following installed:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Apache Tomcat 11](https://tomcat.apache.org/download-11.cgi)
- [Firebird](https://firebirdsql.org/en/firebird-5-0/) (database)
- [Eclipse IDE for Enterprise Java](https://www.eclipse.org/downloads/)

## 🚀 How to run in Eclipse

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/BestCheff.git
   ```

2. Open Eclipse and go to:
   `File → Import → Existing Projects into Workspace`

3. Select the cloned project folder.

4. Configure the Tomcat 11 server at:
   `Window → Preferences → Server → Runtime Environments`

5. Set up the Firebird database connection in the project properties file.

6. Right-click the project and select:
   `Run As → Run on Server`, then choose Tomcat 11.

## 🗄️ Database

The system uses **Firebird** as its database. Make sure the Firebird service is running before starting the application.

The connection is made via **JDBC** using the **Jaybird** driver.

## 📁 Project Structure

```
BestCheff/
├── src/
│   └── main/
│       ├── java/        # Servlets and business logic
│       └── webapp/      # JSP, CSS, JS
├── BaseDados/           # SQL scripts / database structure
├── .gitignore
└── README.md
```

## 👤 Author

Developed by **Aleph Enzo Guimarães da Silva**
