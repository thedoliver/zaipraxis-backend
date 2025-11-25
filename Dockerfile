# === Build (Maven) ===
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -e -DskipTests package

# === Run (JRE) ===
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# jar final (ajuste o nome se diferente)
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Profile dev por padrão; a URL virá de MONGO_URI
ENV SPRING_PROFILES_ACTIVE=$dev
ENV MONGODB_URI=${MONGODB_URI}
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
