
## url to test
http://localhost:8888/service-hi/dev  
or  
http://localhost:8888/service-hi/pr

## error cannot clone or checkout
add this in application.properties
```$xslt
spring.cloud.config.server.git.skip-ssl-validation=true
```
https://github.com/spring-guides/gs-centralized-configuration/issues/10

## Docker build command
```$xslt
docker build --rm=true . --no-cache --tag=yao/config-server:latest
```

## run docker image with environment variable
```$xslt
docker run -p 18888:8888 --name=config-server yao/config-server:latest
```
visit http://localhost:18888