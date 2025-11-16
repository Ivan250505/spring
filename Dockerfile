FROM openjdk:21
COPY "./target/futbol-1.jar.original" "app.jar"
EXPOSE 8103
ENTRYPOINT ["java", "-jar", "app.jar"]