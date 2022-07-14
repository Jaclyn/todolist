FROM maven:3.6.3-jdk-8
# copy the source tree and the pom.xml to our new container
COPY ./ ./
# package our application code
RUN mvn clean package
RUN apk --no-cache add ca-certificates

# set the startup command to execute the jar
CMD ["java", "-jar", "target/todolist-0.0.1-SNAPSHOT.jar"]