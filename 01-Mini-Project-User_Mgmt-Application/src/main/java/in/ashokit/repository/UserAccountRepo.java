package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.ashokit.entities.UserAccount;
import jakarta.transaction.Transactional;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {
	
//	@Modifying
//	@Transactional
//	@Query("update UserAccount set activeSw=:status where userId=:userId")
//	public void updateUserAccStatus(Integer userId, String status);
//	
//	
	@Modifying
	@Transactional
	@Query("UPDATE UserAccount ua SET ua.activeSw = :status WHERE ua.userid = :userId")
	void updateUserAccStatus(@Param("userId") Integer userId, @Param("status") String status);

}
