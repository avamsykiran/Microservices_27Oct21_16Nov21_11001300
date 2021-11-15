package in.budgettracker.ums.service;

import java.util.List;

import in.budgettracker.ums.entity.UserEntity;
import in.budgettracker.ums.exception.UserManagementException;

public interface UserService {
	List<UserEntity> getAll();
	UserEntity getById(Long id);
	UserEntity getByEmailId(String emailId);
	UserEntity add(UserEntity user) throws UserManagementException;
	UserEntity update(UserEntity user) throws UserManagementException;
	void deleteById(Long id) throws UserManagementException;
}
