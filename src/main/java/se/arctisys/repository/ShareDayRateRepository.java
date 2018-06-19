package se.arctisys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ShareDayRate;

@Repository
public interface ShareDayRateRepository extends JpaRepository<ShareDayRate, Long> {
	//TODO
	/**
	 * Method to get list of dayrates between two dates for a certain share.
	 */	
	@Query("SELECT s FROM ShareDayRate s WHERE actualDate >= :startDate AND actualDate <= :endDate AND share.id = :shareId ORDER BY actualDate")
    public List<ShareDayRate> getDayRatesBetweenTwoDatesForShare(@Param("startDate") Date startDate, 
    		@Param("endDate") Date endDate,
    		@Param("shareId") String shareId);
}
