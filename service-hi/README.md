
## SERVER_PORT is from spring cloud config
run the config-server before run this app, otherwise replace ${SERVER_PORT} with hardcoded port number.  
the default spring.cloud.config.profile is dev (see application.yml) 
to run in "pr" environment, it can be overwritten by runtime argument -Dspring.cloud.config.profile=pr, now start the application, it is running on port 18763

## to get properties from spring cloud config 
add dependency 
```$xslt
<dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

## Docker build command
```$xslt
docker build --rm=true . --no-cache --tag=yao/service-hi:latest
```

## run docker image with environment variable
```$xslt
docker run -p 18763:8763 -e SPRING_CONFIG_URI=http://192.168.46.17:18888 -e SPRING_EUREKA_SERVER_URI=http://192.168.46.17:18761/eureka/ --name=service-hi yao/service-hi:latest
```
visit http://localhost:18763

## bootstrap.properties
The properties to configure the Config Client must necessarily be read in before the rest of the application’s configuration is read from the Config Server, during the bootstrap phase. Specify the client’s spring.application.name and the location of the Config Server (spring.cloud.config.uri) in src/main/resources/bootstrap.properties, where it will be loaded earlier than any other configuration. The following listing shows that file:  
https://spring.io/guides/gs/centralized-configuration/  

bootstrap.properties
```
spring.application.name=service-hi
spring.cloud.config.uri=${SPRING_CONFIG_URI:http://localhost:8888}
```