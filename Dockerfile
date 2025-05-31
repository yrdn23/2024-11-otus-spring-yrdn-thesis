FROM bellsoft/liberica-openjdk-alpine-musl:21.0.1
COPY /target/school-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]