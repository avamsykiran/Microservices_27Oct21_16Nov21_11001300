package in.budgettracker.tms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.budgettracker.tms.entity.TransactionEntity;
import in.budgettracker.tms.entity.TransactionType;
import in.budgettracker.tms.entity.UserEntity;
import in.budgettracker.tms.exception.TxnManagementException;
import in.budgettracker.tms.repo.TransactionRepo;
import in.budgettracker.tms.repo.UserRepo;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TransactionRepo txnRepo;
	
	@Override
	public List<TransactionEntity> getAllByUserId(Long userId) throws TxnManagementException {
		return txnRepo.getAllTransactionsByUser(userId);
	}

	@Override
	public TransactionEntity getById(Long txnId) throws TxnManagementException {
		return txnRepo.findById(txnId).orElse(null);
	}

	@Override
	public TransactionEntity create(TransactionEntity entity) throws TxnManagementException {
		UserEntity user = userRepo.findById(entity.getHolder().getUserId()).orElse(null);
		
		if(user==null)
			throw new TxnManagementException("User Account Not Found");
		
		entity.setHolder(user);
		if(entity.getTxnType()==TransactionType.CREDIT)
			user.setCurrentBal(user.getCurrentBal()+entity.getAmount());
		else
			user.setCurrentBal(user.getCurrentBal()-entity.getAmount());
		
		entity = txnRepo.save(entity);
		userRepo.save(user);
		return entity;
	}

	@Override
	public void deleteById(Long txnId) throws TxnManagementException {
		TransactionEntity txn = txnRepo.findById(txnId).orElse(null);
		
		if(txn==null)
			throw new TxnManagementException("Transaction not found");
		
		UserEntity user = txn.getHolder();
		
		if(txn.getTxnType()==TransactionType.CREDIT)
			user.setCurrentBal(user.getCurrentBal()-txn.getAmount());
		else
			user.setCurrentBal(user.getCurrentBal()+txn.getAmount());
		
		txnRepo.delete(txn);
		userRepo.save(user);
	}

	@Override
	public List<TransactionEntity> getAllByUserId(Long userId, LocalDateTime start, LocalDateTime end)
			throws TxnManagementException {
		return txnRepo.getAllTransactionsByUserInDates(userId, start, end);
	}

}
