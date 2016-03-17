package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.StockHolding;

@Repository
public interface StockHoldingRepository extends JpaRepository<StockHolding, Long> {
	
}
