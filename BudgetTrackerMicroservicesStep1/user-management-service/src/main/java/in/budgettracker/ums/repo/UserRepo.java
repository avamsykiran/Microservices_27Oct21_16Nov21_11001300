package in.budgettracker.ums.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.budgettracker.ums.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

	boolean existsByEmailId(String emailId);
	Optional<UserEntity> findByEmailId(String emailId);
}
