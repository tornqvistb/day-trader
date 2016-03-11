package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ShareDayRate;

@Repository
public interface ShareDayRateRepository extends JpaRepository<ShareDayRate, Long> {
	
	
}
