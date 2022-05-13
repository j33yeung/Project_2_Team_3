FROM openjdk:8
COPY ./target/LocationSearchAPI.jar/ /tmp
WORKDIR /tmp
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "LocationSearchAPI.jar"]