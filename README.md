# KitGithubIntegration

##Getting Started

### Development

1. For increasing the throughput of this application I use Spring Async and  Completable Future
,so for fetching the repository names of a user and save we can serve the request asynchronously.

2. The application can run in different active profiles dev,prod.

3. For testing the API and documentation of the rest API , this application is integrated with swagger 

4. FOR testing the API the REST Assured has been used

5. The application runs with the embedded MongoDB

6. The application can be run on the docker 

### Jar Packaging

To build the final jar and optimize the KitGithubIntegration application for production, run:

```

./mvnw -Pprod clean verify


```

For building with swagger enabled :

```

./mvnw -Pprod,swagger clean verify

```

Run the application by this command :

```

java -jar target/*.jar


```

Navigate to [http://localhost:8080](http://localhost:8080) in browser.

## Testing

To run the tests :

```
./mvnw verify
```
## Swagger Test

Swagger tests are at this url :

```
http://localhost:8080/swagger-ui.html

```

## Docker


First start a mongodb database in a docker container:

```
docker-compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove:

```
docker-compose -f src/main/docker/mongodb.yml down
```

Run the whole application and dependecies first create docker : 

```
./mvnw -Pprod verify jib:dockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```
For monitoring the app in docker : 

```
docker-compose -f src/main/docker/monitoring.yml up -d
```
