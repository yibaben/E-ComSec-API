FROM openjdk:8

COPY target/LiveCodingv2-0.0.1-SNAPSHOT.jar LiveCodingv2.jar

ENTRYPOINT ["java", "-jar", "/LiveCodingv2.jar"]