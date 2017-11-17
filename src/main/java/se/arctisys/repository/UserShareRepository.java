package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.UserShare;

@Repository
public interface UserShareRepository extends JpaRepository<UserShare, Long> {
	
}
