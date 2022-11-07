FROM openjdk:8-jre-slim
EXPOSE 2700/tcp
RUN mkdir /app
RUN mkdir /app/config

COPY target/*.jar /app/
COPY config/*.* /app/config/

WORKDIR "/app"
ENTRYPOINT ["java", "-jar", "devops-integration.jar", "&&", "tail", "-f", "/dev/null"]