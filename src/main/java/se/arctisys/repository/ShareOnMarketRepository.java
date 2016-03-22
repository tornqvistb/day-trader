package se.arctisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ShareOnMarket;

@Repository
public interface ShareOnMarketRepository extends JpaRepository<ShareOnMarket, String> {
	
	@Query("SELECT s FROM ShareOnMarket s WHERE s.status = :status")
    public List<ShareOnMarket> findSOMByStatus(@Param("status") String status);
	
}
