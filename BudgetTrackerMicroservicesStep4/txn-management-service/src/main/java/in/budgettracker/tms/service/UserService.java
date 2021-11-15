package in.budgettracker.tms.service;

import in.budgettracker.tms.exception.TxnManagementException;
import in.budgettracker.tms.model.UserModel;

public interface UserService {
	UserModel getUserById(Long userId) throws TxnManagementException;
	UserModel createUser(UserModel user) throws TxnManagementException;
}
