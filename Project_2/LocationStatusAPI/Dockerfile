FROM openjdk:8
COPY ./target/LocationStatusAPI.jar/ /tmp
WORKDIR /tmp
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "LocationStatusAPI.jar"]