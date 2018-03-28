package se.arctisys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.arctisys.domain.User;

/**
 * Created by tornqvistb on 2018-03-28.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
    public List<User> findUsersByEmail(@Param("email") String email);
}
