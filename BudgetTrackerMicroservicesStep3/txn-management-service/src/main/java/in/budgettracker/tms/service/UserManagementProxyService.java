package in.budgettracker.tms.service;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.budgettracker.tms.model.UserModel;

@FeignClient(name="user-management-service")
@LoadBalancerClient(name = "user-management-service")
public interface UserManagementProxyService {

	@GetMapping("/users/{userId}")
	UserModel getUserById(@PathVariable("userId") Long userId);
	
	@PostMapping("/users")
	UserModel createUser(UserModel user);
}
