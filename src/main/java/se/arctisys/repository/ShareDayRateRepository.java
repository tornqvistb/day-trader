package se.arctisys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.Share;
import se.arctisys.domain.ShareDayRate;
import se.arctisys.domain.User;

@Repository
public interface ShareDayRateRepository extends JpaRepository<ShareDayRate, Long> {
	//TODO
	/**
	 * Method to get list of dayrates between two dates for a certain share.
	 */	
	@Query("SELECT s FROM ShareDayRate s WHERE 1 = 1")
    public List<User> getDayRatesBetweenTwoDatesForShare(@Param("startDate") Date startDate, 
    		@Param("endDate") Date endDate,
    		@Param("share") Share share);
}
