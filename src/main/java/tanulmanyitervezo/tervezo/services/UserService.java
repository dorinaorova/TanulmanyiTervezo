package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(int id){
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return  repository.findByEmail(email);
    }

    public User addUser(User newUser){
        return repository.save(newUser);
    }
}
