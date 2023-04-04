package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration()
public class EcomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApiApplication.class, args);
	}

}
