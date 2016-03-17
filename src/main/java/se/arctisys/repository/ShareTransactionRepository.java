package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ShareTransaction;

@Repository
public interface ShareTransactionRepository extends JpaRepository<ShareTransaction, Long> {
	
}
