package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
}
