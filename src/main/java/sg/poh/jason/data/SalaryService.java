package sg.poh.jason.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.poh.jason.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    SalaryRepository repository;

    public User add(User user) {
        Optional<User> dbUser = repository.findUserByName((user.getName()));
        User tempUser = null;
        if (dbUser.isPresent()) {
            tempUser = dbUser.get();
            tempUser.setSalary(user.getSalary());
            tempUser = repository.save(tempUser);
        } else {
            tempUser = repository.save(user);
        }
        return tempUser;
    }

    public List<User> getSalaries() {
        return (List<User>) repository.findAll();
    }
}
