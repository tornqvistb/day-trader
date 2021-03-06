package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ShareHolding;

@Repository
public interface StockHoldingRepository extends JpaRepository<ShareHolding, Long> {
	
}
