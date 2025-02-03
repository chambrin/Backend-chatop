
# Backend Chatop

![](https://user.oc-static.com/upload/2022/10/25/1666686016025_P3_Banner_V2.png)

## Description

Backend développé avec le framework **Spring Boot**.

## Installation

```bash
mvn clean install
```

## Utilisation

```bash
mvn spring-boot:run
```
## Création de la base de données en local

1. **Installer MySQL** : Assurez-vous que MySQL est installé sur votre machine. Vous pouvez le télécharger et l'installer depuis [MySQL Downloads](https://dev.mysql.com/downloads/).

2. **Créer la base de données** : Connectez-vous à MySQL et créez la base de données `chatop`.

    ```sql
    CREATE DATABASE chatop;
    ```

3. **Configurer les informations de connexion** : Mettez à jour le fichier `src/main/resources/application.properties` avec vos informations de connexion MySQL.

    ```ini
    spring.datasource.url=jdbc:mysql://localhost:8889/chatop?useSSL=false&allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=root
    ```

4. **Initialiser la base de données** : Les tables et les données initiales seront créées automatiquement lors du démarrage de l'application grâce au fichier `src/main/resources/data.sql`.

5. **Démarrer l'application** : Utilisez Maven pour démarrer l'application, ce qui initialisera la base de données.

    ```bash
    mvn spring-boot:run
    ```

6. **Vérifier la base de données** : Vous pouvez vérifier que les tables ont été créées et que les données ont été insérées en vous connectant à la base de données `chatop` et en exécutant des requêtes SQL.

    ```sql
    USE chatop;
    SHOW TABLES;
    SELECT * FROM USERS;
    SELECT * FROM RENTALS;
    SELECT * FROM MESSAGES;
    ```

## Connexion a la base de données

Les informations de connexion à la base de données doivent être renseignées dans le fichier `.env` de l'application.

---

## Documentation Swagger

Consultable a l'adresse suivante : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)