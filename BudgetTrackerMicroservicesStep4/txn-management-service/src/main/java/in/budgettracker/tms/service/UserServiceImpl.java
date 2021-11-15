package in.budgettracker.tms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger;
	
	public UserServiceImpl(){
		this.logger= LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public UserModel getUserById(Long userId) throws TxnManagementException {
		UserModel userModel=proxy.getUserById(userId);
		UserEntity userEntity = userRepo.findById(userId).orElse(null);
		if(userEntity!=null) {
			if(userModel!=null) {
				userModel.setCurrentBal(userEntity.getCurrentBal());
			}else {
				userModel = new UserModel(userId, null, null, userEntity.getCurrentBal());
			}
		}else if(userModel!=null){
			userRepo.save(new UserEntity(userModel.getUserId(), 0.0, null));
			userModel.setCurrentBal(0.0);	
		}
		
		return userModel;
	}

	@Override
	public UserModel createUser(UserModel user) throws TxnManagementException {
		logger.debug(user.toString());
		if(user.getUserId()!=null && userRepo.existsById(user.getUserId()))
			throw new TxnManagementException("Duplicate User Id");
		
		UserModel userModel=null;
		
		if(user.getUserId()!=null)
		 userModel = proxy.getUserById(user.getUserId());
		
		if(userModel==null) {
			userModel = proxy.createUser(user);
		}
		
		userRepo.save(new UserEntity(userModel.getUserId(), 0.0, null));
		userModel.setCurrentBal(0.0);
		return userModel;
	}

}
