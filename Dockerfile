FROM openjdk:17.0.2-oracle
ADD /build/libs/clevertec-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]