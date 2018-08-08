package my.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.spring.cloud.service.HelloService;

@RestController
public class HelloControler {
	@GetMapping(value = "/test")
    public String test(@RequestParam String name) {
		System.out.println("receive request: " + name);
        return name;
    }
	
	@Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }
}
