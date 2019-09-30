package sg.poh.jason.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sg.poh.jason.model.User;

import java.util.Optional;

@Repository
public interface SalaryRepository
        extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);
}