package in.budgettracker.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.budgettracker.tms.exception.TxnManagementException;
import in.budgettracker.tms.model.UserModel;
import in.budgettracker.tms.service.UserService;

@RestController
@RequestMapping("/users")
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserModel> getById(@PathVariable("userId") Long userId) throws TxnManagementException {
		UserModel model = userService.getUserById(userId);
		return model==null? new ResponseEntity<>(HttpStatus.NOT_FOUND):ResponseEntity.ok(model);
	}
	
	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) throws TxnManagementException {
		user = userService.createUser(user);
		return new ResponseEntity<UserModel>(user, HttpStatus.CREATED);
	}
}
