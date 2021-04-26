FROM openjdk:11
WORKDIR /
ADD target/controlled-drinking-0.9.6-SNAPSHOT.jar app.jar
RUN useradd -m admin
USER admin
EXPOSE 80
CMD java -jar -Dspring.profiles.active=prod app.jar