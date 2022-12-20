FROM gradle:latest as stageone
WORKDIR /tmp
COPY . .
RUN ./gradlew build

FROM bellsoft/liberica-openjdk-alpine:17
COPY --from=stageone /tmp/build/libs/pawpals-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pawpals-0.0.1-SNAPSHOT.jar"]
