package se.iths.connie.movierater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.connie.movierater.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
