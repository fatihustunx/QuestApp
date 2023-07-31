FROM openjdk:11

COPY target/questapp-0.0.1-SNAPSHOT.jar questapp.jar

ENTRYPOINT ["java","-jar","/questapp.jar"]