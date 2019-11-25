
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