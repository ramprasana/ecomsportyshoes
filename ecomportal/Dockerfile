# Build a JAR file
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /home/app
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean package

# Create an image
From openjdk:14
EXPOSE 8090
#copy ./target/ecomportal-0.0.1-SNAPSHOT.jar ecomportal-0.0.1-SNAPSHOT.jar
copy --from=build /home/app/target/ecomportal-0.0.1-SNAPSHOT.jar ecomportal-0.0.1-SNAPSHOT.jar
#CMD ["java","-jar","ecomportal-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["sh", "-c", "java -jar /ecomportal-0.0.1-SNAPSHOT.jar"]