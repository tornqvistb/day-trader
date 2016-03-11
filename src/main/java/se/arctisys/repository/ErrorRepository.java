package se.arctisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.ErrorRecord;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorRecord, Long> {

	@Query("SELECT e FROM ErrorRecord e WHERE e.archived = :archived")
    public List<ErrorRecord> findErrorsByArchived(@Param("archived") boolean archived);

	
}
