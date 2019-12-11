
## spring profile
create the application.docker.yml for docker  
To run it, java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker

## Docker build command
```$xslt
docker build --rm=true . --no-cache --tag=yao/eureka-server:latest
```

## run docker image with environment variable
```$xslt
docker run -p 18761:8761 -e spring_profiles_active=docker --name=eureka-server yao/eureka-server:latest
```
visit http://localhost:18761

