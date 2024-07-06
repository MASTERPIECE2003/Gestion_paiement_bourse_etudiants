# Gestion de Paiement de Bourses des Étudiants

Ce projet est une application web complète pour gérer les paiements des bourses des étudiants. Il inclut une interface utilisateur construite avec React et Material-UI ainsi qu'un backend développé avec Spring Boot. L'application permet d'ajouter, de modifier et de supprimer des paiements, de générer des reçus, et d'envoyer des notifications par email.

## Fonctionnalités

- Gestion des étudiants et des paiements
- Ajout, modification et suppression de paiements
- Génération de reçus de paiement
- Envoi de notifications par email

## Technologies Utilisées

### Frontend

- **React** : Une bibliothèque JavaScript pour construire des interfaces utilisateur.
- **Material-UI** : Une bibliothèque de composants React pour une conception rapide et réactive.
- **Axios** : Pour effectuer des requêtes HTTP vers le backend.

### Backend

- **Spring Boot** : Un framework Java pour créer des applications backend robustes.
- **Spring Data JPA** : Pour la gestion de la persistance des données.
- **Spring Mail** : Pour l'envoi des emails.
- **PostgreSQL** : Système de gestion de base de données relationnelle.

## Prérequis

- Java 11 ou supérieur
- Node.js et npm
- PostgreSQL
- Git

## Installation

### Backend

1. Clonez le dépôt :

    ```bash
    git clone https://github.com/votre-utilisateur/votre-depot.git
    cd votre-depot/gestionbourse
    ```

2. Configurez la base de données PostgreSQL dans `application.properties` :

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/votre_base_de_donnees
    spring.datasource.username=votre_utilisateur
    spring.datasource.password=votre_mot_de_passe
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Configurez les paramètres SMTP pour l'envoi des emails dans `application.properties` :

    ```properties
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=votre_email@gmail.com
    spring.mail.password=votre_mot_de_passe
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```

4. Lancez l'application Spring Boot :

    ```bash
    ./mvnw spring-boot:run
    ```

### Frontend

1. Accédez au répertoire frontend :

    ```bash
    cd ../gestionboursefront
    ```

2. Installez les dépendances :

    ```bash
    npm install
    ```

3. Lancez l'application React :

    ```bash
    npm start
    ```

## Utilisation

1. Accédez à `http://localhost:3000` pour utiliser l'application frontend.
2. Utilisez les fonctionnalités de gestion des étudiants et des paiements à travers l'interface utilisateur.

## Contribuer

Les contributions sont les bienvenues ! Veuillez soumettre une issue ou une pull request pour toute amélioration ou suggestion.

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
