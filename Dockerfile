FROM openjdk:8
ADD target/csp.jar csp.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar", "csp.jar"]
