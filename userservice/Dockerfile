FROM java:8-jre
WORKDIR usr/src
ENV  MYSQL_DATABASENAME=cplayersdb
ENV  MYSQL_URL=jdbc:mysql://localhost:3306/cplayersdb?createDatabaseIfNotExist=true
ENV  MYSQL_USERNAME=admin
ENV  MYSQL_PASSWORD=admin123
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]
