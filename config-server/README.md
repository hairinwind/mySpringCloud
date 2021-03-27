
## url to test
http://localhost:8888/service-hi/dev  
or  
http://localhost:8888/service-hi/pr

## git based backend
```
spring.cloud.config.server.git.skip-ssl-validation=true
spring.cloud.config.server.git.uri=https://github.com/hairinwind/mySpringCloud.git
spring.cloud.config.server.git.searchPaths=mySpringCloudConfig
spring.cloud.config.label=master
spring.cloud.config.server.git.username=
spring.cloud.config.server.git.password=
```

## file based backend
```
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations=classpath:/config,classpath:/config/{application}
```
This is using the files from classpath.  
If you put the files under config folder, the file name shall be {application}-{profile}.properties. The example here is that application is service-hi and the profile is dev.  
Or you can create a subfolder called {application} and the file in that folder is application-{profile}.properties. 


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

