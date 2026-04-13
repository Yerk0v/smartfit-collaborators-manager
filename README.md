Smartfit web app for collaborators.

Just imagine how much task does have one traineer around the gym... or imagine if you have to use one room and you can't come to an agreement with other traineer. Well, here you'll find the solution for those little task's that makes the difference between one normal gym and one SMART-GYM!

Almost ready, check some new functionalities at https://smartfit-collaborators-web.herokuapp.com/login ;)

Stack:

- Spring Boot
- MySQL 
- Git
- Github
- SonarQube
- Trello
- Selenium

![Login](https://user-images.githubusercontent.com/71664180/180708139-863b27bc-3953-4831-9b0f-dff0c80d5514.png)
![Menu](https://user-images.githubusercontent.com/71664180/180708164-7a7b7c98-0718-4baf-9fe4-f70396c89216.png)
![SalasGithub](https://user-images.githubusercontent.com/71664180/180708199-393a8208-dc92-48e2-814f-748672977663.png)
![ReservarSala](https://user-images.githubusercontent.com/71664180/180708276-127c21cf-2fa6-44cb-a833-7df235c8e951.png)

**Run & Requirements**

- **Java:** AdoptOpenJDK / OpenJDK 17 (project compiled for Java 17). Set `JAVA_HOME` accordingly.
- **Spring Boot:** This project uses Spring Boot 3 — the code uses Jakarta APIs (jakarta.persistence, Spring Security 6 compatible libs).
- **Build tool:** Maven (the project includes the Maven wrapper `mvnw`).
- **Database:** MySQL running locally (default config in `src/main/resources/application.properties`). Create a database named `smartfit_db` or update the URL there.
- **Recommended memory:** 1GB+ for running the app and MySQL locally.

- **Quick start (development):**

```bash
# start a local MySQL instance and create the database `smartfit_db`
# (update credentials in src/main/resources/application.properties if different)
./mvnw -DskipTests spring-boot:run
```

- **Bootstrap admin account:** The app creates a local admin when `app.bootstrap-admin.enabled=true` and the DB is empty. Defaults are in `src/main/resources/application.properties`:

- **Email:** admin@local.dev (override with env `BOOTSTRAP_ADMIN_EMAIL`)
- **Password:** admin123 (override with env `BOOTSTRAP_ADMIN_PASSWORD`)

- **Development notes:**
	- Templates are served from `src/main/resources/templates` during development (see `application.properties`) so editing HTML should refresh the app when running with DevTools.
	- LiveReload is enabled by DevTools on port `35729`; use a LiveReload browser extension or allow the in-page script in development.

**Files of interest**
- Spring Boot entry: [src/main/java/com/smartfit/app/smartfitmanager/SmartfitManagerApplication.java](src/main/java/com/smartfit/app/smartfitmanager/SmartfitManagerApplication.java#L1)
- App properties: [src/main/resources/application.properties](src/main/resources/application.properties#L1-L40)
- Security config: [src/main/java/com/smartfit/app/smartfitmanager/Security/SecurityConfiguration.java](src/main/java/com/smartfit/app/smartfitmanager/Security/SecurityConfiguration.java#L1-L200)
- User repository: [src/main/java/com/smartfit/app/smartfitmanager/Repository/UserRepo.java](src/main/java/com/smartfit/app/smartfitmanager/Repository/UserRepo.java#L1-L40)
 
**Docker / Containerized development**

Build & run with Docker Compose:

```bash
docker compose up --build
```

This starts a MySQL 8 container and the app (port 8080). The compose file sets DB creds and the app is configured via environment variables:

- `SPRING_DATASOURCE_URL` (default points to the `db` service)
- `SPRING_DATASOURCE_USERNAME` / `SPRING_DATASOURCE_PASSWORD`
- `BOOTSTRAP_ADMIN_EMAIL` / `BOOTSTRAP_ADMIN_PASSWORD`

Notes:
- The Docker image is built using a Maven build stage (uses the Maven wrapper in the repo), then copied into a Temurin JRE base image.
- For production you might prefer building the artifact in CI and using a smaller runtime image only, or using a distroless JRE.

