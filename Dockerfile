FROM maven:3.8.4-openjdk-11 as maven-base
LABEL version 3.8.4-11

RUN apt-get update && \
    apt-get -y install git && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /home/reqdoc/
COPY . .
RUN mvn clean install
FROM openjdk:11-jre-slim
RUN apt-get install -y ca-certificates
WORKDIR /home/reqdoc/
COPY --from=maven-base  /home/reqdoc/target/reqdoc-1.0-SNAPSHOT-jar-with-dependencies.jar ./
CMD ["java", "-jar", "/home/reqdoc/reqdoc-1.0-SNAPSHOT-jar-with-dependencies.jar"]
