FROM openjdk:17
VOLUME /tmp
EXPOSE 8761
ADD ./target/Gateway-1.0.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]