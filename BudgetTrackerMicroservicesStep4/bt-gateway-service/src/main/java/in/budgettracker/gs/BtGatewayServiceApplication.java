package in.budgettracker.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BtGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtGatewayServiceApplication.class, args);
	}

}
