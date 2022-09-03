From openjdk:8
copy ./target/api-taurus-h.jar api-taurus-h.jar
CMD ["java","-jar","api-taurus-h.jar"]