package in.budgettracker.tms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.budgettracker.tms.entity.TransactionEntity;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

}
