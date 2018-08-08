# spring cloud practice
https://blog.csdn.net/forezp/article/details/81040925  

## Eureka server
The Eureka server is a service discovery pattern implementation, where every microservice is registered and a client microservice looks up the Eureka server to get a dependent microservice to get the job done.
The Eureka server itself is a microservice which treats itself as a Eureka client.
The settings in application.yml declare it is a server
```
registerWithEureka: false
fetchRegistry: false
``` 
### cluster Eureka server
https://blog.csdn.net/forezp/article/details/81041101  



## Eureka client
Eureka client is the microservice registered on Eureka server.
Service-hi is the eureka client here. 
Run them on default port can mimic a cluster microservice.

## ribbon
https://github.com/Netflix/ribbon  
Ribbon is a client side load balancer which gives you a lot of control over the behaviour of HTTP and TCP clients.
Feign already includes ribbon.
RestTemplate can be automatically configured to use ribbon. To create a load-balanced RestTemplate, create a RestTemplate @Bean and use the @LoadBalanced qualifier.
It is looking up the microservice by its name "SERVICE-HI" through the eureka server. 
```
restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
```

## Feign
https://github.com/OpenFeign/feign  
Netflix provides Feign as an abstraction over REST-based calls, by which microservices can communicate with each other, but developers don't have to bother about REST internal details.
Feign already integrates ribbon, so it has load balance.
Feign already integrates Hystrix
Compare the restTemplte code above, the Feign code does not need to deal with http://SERVCIE-HI... It is using a interface to wrap the http request. 
```
@FeignClient(value = "service-hi")  //the service name is not case sensitive
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
```

## Hystrix
https://github.com/Netflix/Hystrix  
Hystrix is a latency and fault tolerance library. Netflix has created a library called Hystrix that implements the circuit breaker pattern.
### enable Hystrix in ribbon
add @EnableHystrix on boot application class
add @HystrixCommand on service method
create the fallback method
```
@HystrixCommand(fallbackMethod = "hiError")
public String hiService(String name) {
    return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
}

public String hiError(String name) {
    return "hi,"+name+",sorry,error!";
}
```
Once the service is not available, the hiError method is executed as fallback.

### enable Hystrix in Feign
enable it in configuration
```
feign:
  hystrix:
    enabled: true
```
provide fallback class
```
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
```
```
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
```

### Hystrix Dashboard
minitor the failed service
//TODO

### Hystrix Turbine


## Zuul
https://github.com/Netflix/zuul  
Zuul is a gateway service that provides dynamic routing, monitoring, resiliency, security, and more.
The routes are added in configuratin file
```
zuul:
  routes:
    api-a:
      path: /api-a/**
      serviceId: service-ribbon
    api-b:
      path: /api-b/**
      serviceId: service-feign
```

### filter
//TODO

## Spring Cloud Sleuth
Add sleuth to the classpath of a Spring Boot application, and you will see the correlation data being collected in logs, as long as you are logging requests.
It helps you understand the dependencies between microservcies, serviceA => serviceB => serviceC...







