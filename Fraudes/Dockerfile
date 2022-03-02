FROM openjdk:17
VOLUME /tmp
EXPOSE 8761
ADD ./target/Fraudes-1.0.jar fraudes.jar
ENTRYPOINT ["java", "-jar", "/fraudes.jar"]
