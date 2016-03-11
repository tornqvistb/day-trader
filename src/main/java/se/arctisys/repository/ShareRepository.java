package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, String> {
	
}
