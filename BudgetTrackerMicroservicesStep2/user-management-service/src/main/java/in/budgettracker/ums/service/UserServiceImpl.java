package in.budgettracker.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.budgettracker.ums.entity.UserEntity;
import in.budgettracker.ums.exception.UserManagementException;
import in.budgettracker.ums.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<UserEntity> getAll() {
		return userRepo.findAll();
	}

	@Override
	public UserEntity getById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public UserEntity getByEmailId(String emailId) {
		return userRepo.findByEmailId(emailId).orElse(null);
	}

	@Override
	public UserEntity add(UserEntity user) throws UserManagementException {
		if(user.getUserId()!=null && userRepo.existsById(user.getUserId())) {
			throw new UserManagementException("Duplicate User Id");
		}
		
		if(user.getEmailId()!=null && userRepo.existsByEmailId(user.getEmailId())) {
			throw new UserManagementException("Duplicate Email Id");
		}
		
		return userRepo.save(user);
	}

	@Override
	public UserEntity update(UserEntity user) throws UserManagementException {
		
		UserEntity oldRecord = userRepo.findById(user.getUserId()).orElse(null);
		
		if(oldRecord==null) {
			throw new UserManagementException("User Not Found");
		}
		
		if(!oldRecord.getEmailId().equals(user.getEmailId()) && userRepo.existsByEmailId(user.getEmailId())) {
			throw new UserManagementException("Duplicate Email Id");
		}
		
		return userRepo.save(user);
	}

	@Override
	public void deleteById(Long id) throws UserManagementException {
		if(!userRepo.existsById(id))
			throw new UserManagementException("User Not Found");
		
		userRepo.deleteById(id);
	}

}
