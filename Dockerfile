FROM openjdk:17-jdk-alpine3.13
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "-Djdk.tls.client.protocols=TLSv1.2","/app.jar"]