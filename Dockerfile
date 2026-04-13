FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY mvnw pom.xml .
COPY .mvn .mvn
RUN ./mvnw -v

COPY src ./src
# Ensure MAVEN_CONFIG doesn't inject an unexpected arg (some images set it to a path)
RUN MAVEN_CONFIG= ./mvnw -DskipTests package -B

FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
