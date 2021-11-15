package in.budgettracker.tms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.budgettracker.tms.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{

}
