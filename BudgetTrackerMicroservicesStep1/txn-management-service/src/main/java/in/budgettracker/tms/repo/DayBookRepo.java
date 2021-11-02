package in.budgettracker.tms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.budgettracker.tms.entity.DayBookEntity;

public interface DayBookRepo extends JpaRepository<DayBookEntity, String> {

}
