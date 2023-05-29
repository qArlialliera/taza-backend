FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/ROOT.jar /usr/local/lib/ROOT.jar
EXPOSE 8989
ENTRYPOINT ["java","-jar","/usr/local/lib/ROOT.jar"]