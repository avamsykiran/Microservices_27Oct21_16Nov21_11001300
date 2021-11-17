package in.budgettracker.tms.service;

import java.time.LocalDateTime;
import java.util.List;

import in.budgettracker.tms.entity.TransactionEntity;
import in.budgettracker.tms.exception.TxnManagementException;

public interface TransactionService {
	List<TransactionEntity> getAllByUserId(Long userId) throws TxnManagementException;
	List<TransactionEntity> getAllByUserId(Long userId,LocalDateTime start,LocalDateTime end) throws TxnManagementException;
	TransactionEntity getById(Long txnId)  throws TxnManagementException;
	TransactionEntity create(TransactionEntity entity) throws TxnManagementException;
	void deleteById(Long txnId) throws TxnManagementException;
}
