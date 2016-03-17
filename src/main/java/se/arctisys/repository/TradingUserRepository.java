package se.arctisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.TradingUser;

@Repository
public interface TradingUserRepository extends JpaRepository<TradingUser, String> {
	
}
