this is the sample project from this tutorial 
https://blog.csdn.net/forezp/article/details/81041078

## download and run the zipkin
download it from https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
```$xslt
java -jar zipkin-server-2.12.9-exec.jar
```
Once it is started, visit http://localhost:9411

## for the service need trace
add dependency and configuration
```$xslt
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```
configuration
```$xslt
spring.zipkin.base-url=http://localhost:9411
```

Run both sleuth-service-hi and sleuth-service-miya, try 
http://localhost:8989/miya and http://localhost:8988/hi  
Then visit http://localhost:9411 again "Find a trace", you can see the trace result.  
Stop the service-miya and visit the service-hi, you would get the error as service-hi needs call service-miya. Check the "Find a trace" again, you would see the error "no response when calling service-miya". 
