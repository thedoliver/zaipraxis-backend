FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia apenas o pom primeiro (cache de dependências)
COPY pom.xml .

# Baixa as dependências (sem compilar ainda)
RUN mvn -q -DskipTests dependency:go-offline

# Agora copia o código fonte
COPY src ./src

# Build do jar (sem testes para acelerar)
RUN mvn -q clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia o jar gerado do stage de build
COPY --from=builder /app/target/zaipraxis-backend-0.0.1-SNAPSHOT.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Perfil default (ajusta se quiser usar "dev")
ENV SPRING_PROFILES_ACTIVE=dev
ENV MONGODB_URI=${MONGODB_URI}

# Comando de entrada
ENTRYPOINT ["sh", "-c", "java $MONGODB_URI-jar app.jar"]
