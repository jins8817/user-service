# User Management

Servizio per la gestione degli utenti di un prodotto.

## Requirements

- Java 22 (JDK 22)
- Maven 3.9+


## Local Start

Avvia UserManagementApplication come una Spring Boot App con i suoi Arguments

```
-Dspring.profiles.active=dev
```

Dopo l'avvio apri la pagina di [swagger](http://localhost:8085/swagger-ui/index.html).

Puoi anche aprirea la [h2-console](http://localhost:8085/h2-console/). La password è nel file application-dev.yml.

## Porte
- **App**: 8081 (dev)
- **H2**: 8081

---


## Profili disponibili

- **dev** → usa **H2 in memory** (porta applicazione **8081**)

> Nota: nel file `src/main/resources/application.yml` il profilo attivo di default è **dev**.

---

## Build
Esegui dalla root del progetto:

```bash
mvn -DskipTests clean package
```
Verrà generato il jar: `target/${project.artifactId}.jar` (es.: `target/usermanagement.jar`).

---

## Porte

- **App**: 8085 (dev)
- **H2**: 8085

---

## Endpoints

Base path: /api/v1/users

- **POST** : /api/v1/users/create -> Body JSON (tutti i campi sono obbligatori)
- **GET** : /api/v1/users/{id}
- **PUT** : /api/v1/users/update/{id} -> Body JSON (tutti i campi sono obbligatori)
- **DELETE** : /api/v1/users/delete/{id}
- **GET** : /api/v1/users
	
