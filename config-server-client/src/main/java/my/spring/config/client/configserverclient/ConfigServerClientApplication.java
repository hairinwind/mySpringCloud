package my.spring.config.client.configserverclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigServerClientApplication {

	@Value("${SERVER_PORT}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerClientApplication.class, args);
	}

	@GetMapping("/config")
	public String getConfig() {
		return serverPort;
	}

}
