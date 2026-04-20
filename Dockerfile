FROM maven:3.9-amazoncorretto-25-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests



FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Admin12345" -p 1433:1433 --name sqlserver -d cesia206s/sql-server:2022


# docker build -t cesia206/springboot-sqlserver:1.0 .

# docker run -d --name springboot-sqlserver -p 8085:8085 cesia206/springboot-sqlserver:1.0

# docker push cesia206/springboot-sqlserver:1.0