package in.budgettracker.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.budgettracker.tms.entity.UserEntity;
import in.budgettracker.tms.exception.TxnManagementException;
import in.budgettracker.tms.model.UserModel;
import in.budgettracker.tms.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserManagementProxyService proxy;
	
	@Override
	public UserModel getUserById(Long userId) throws TxnManagementException {
		UserModel userModel=null;
		UserEntity userEntity = userRepo.findById(userId).orElse(null);
		if(userEntity!=null) {
			userModel = proxy.getUserById(userId);
			if(userModel!=null) {
				userModel.setCurrentBal(userEntity.getCurrentBal());
			}else {
				userModel = new UserModel(userId, null, null, userEntity.getCurrentBal());
			}
		}
		return userModel;
	}

	@Override
	public UserModel createUser(UserModel user) throws TxnManagementException {
		// TODO Auto-generated method stub
		return null;
	}

}
