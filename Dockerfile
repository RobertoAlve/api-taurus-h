FROM openjdk:11
COPY ./target/taurus-magister-0.0.1-SNAPSHOT.jar taurus-magister-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","taurus-magister-0.0.1-SNAPSHOT.jar"]