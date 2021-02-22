FROM adoptopenjdk/openjdk11:ubi
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/cardPayment-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} cardPayment-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/app.jar"]



