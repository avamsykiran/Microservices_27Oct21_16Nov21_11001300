package in.budgettracker.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.budgettracker.ums.entity.UserEntity;
import in.budgettracker.ums.service.UserService;

@RestController
@RequestMapping("/users")
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserEntity>> defaultReqeustHandler(){
		//return new ResponseEntity<List<UserEntity>>(userService.getAll(), HttpStatus.OK);
		return ResponseEntity.ok(userService.getAll());
	}
	
	@GetMapping("/{userId:[0-9]+}")
	public ResponseEntity<UserEntity> getUserByIdHandler(@PathVariable("userId")Long userId){
		UserEntity user = userService.getById(userId);
		//return user==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user); 
		return user==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(user);
	}	
	
	@GetMapping("/{emailId:.+@.+}")
	public ResponseEntity<UserEntity> getUserByEmailIdHandler(@PathVariable("emailId")String emailId){
		UserEntity user = userService.getByEmailId(emailId);
		return user==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(user);
	}	
}
