FROM openjdk:17
COPY --from=build target/Taza-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
