FROM openjdk:17
VOLUME /tmp
EXPOSE 8761
ADD ./target/Eureka-1.0.jar eureka.jar
ENTRYPOINT ["java", "-jar", "/eureka.jar"]
