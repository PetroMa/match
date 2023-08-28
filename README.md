mvn clean install

docker-compose build

docker-compose up java-app

docker exec -it java_db bash
psql -U postgres

http://localhost:8080/swagger-ui.html#/odds45controller