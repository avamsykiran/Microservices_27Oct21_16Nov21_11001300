package in.budgettracker.tms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.budgettracker.tms.model.UserModel;

@FeignClient(name="user-management-service",url = "http://localhost:9100/users")
public interface UserManagementProxyService {

	@GetMapping("/{userId}")
	UserModel getUserById(@PathVariable("userId") Long userId);
	
	@PostMapping
	UserModel createUser(UserModel user);
}
