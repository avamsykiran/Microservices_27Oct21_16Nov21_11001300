package in.budgettracker.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BtDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtDiscoveryServiceApplication.class, args);
	}

}
