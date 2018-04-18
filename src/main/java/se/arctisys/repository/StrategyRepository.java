package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.Strategy;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, String> {
		
}
