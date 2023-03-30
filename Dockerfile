FROM openjdk:17
RUN mkdir /app
COPY ./target/remittance-payout-api-0.0.1-SNAPSHOT.jar /app/payout-api.jar
WORKDIR /app
CMD ["java", "-jar", "payout-api.jar"]