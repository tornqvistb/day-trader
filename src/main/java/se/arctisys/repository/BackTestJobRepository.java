package se.arctisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.BackTestJob;

@Repository
public interface BackTestJobRepository extends JpaRepository<BackTestJob, Long> {
	@Query("SELECT b FROM BackTestJob b WHERE b.status = :status")
    public List<BackTestJob> findByStatus(@Param("status") String status);
}
