package se.arctisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.Share;

@Repository
public interface ShareRepository extends JpaRepository<Share, String> {
	@Query("SELECT s FROM Share s WHERE s.status = :status")
    public List<Share> findByStatus(@Param("status") String status);
}
