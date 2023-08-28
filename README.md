To run the app, first build the Maven project and installs the project files with
`mvn clean install`

Build the docker image `docker-compose build`
and then start and run the app with
`docker-compose up java-app`

The app is running on local machine at port 8080 (http://localhost:8080)
After running the app you can find the operation lists in
```http://localhost:8080/swagger-ui.html```


For access in DB:
```
docker exec -it java_db bash
psql -U postgres
```

