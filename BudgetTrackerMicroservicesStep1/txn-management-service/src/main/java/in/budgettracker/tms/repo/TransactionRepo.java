package in.budgettracker.tms.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.budgettracker.tms.entity.TransactionEntity;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

	@Query("SELECT t FROM TransactionEntity t WHERE t.holder.userId=:userId")
	List<TransactionEntity> getAllTransactionsByUser(Long userId);
	
	@Query("SELECT t FROM TransactionEntity t WHERE t.holder.userId=:userId AND t.dateAndTime BETWEEN :start AND :end")
	List<TransactionEntity> getAllTransactionsByUserInDates(Long userId,LocalDateTime start,LocalDateTime end);
	
}
