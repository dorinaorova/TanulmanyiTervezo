package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(int id){
        return repository.findById(id);
    }

    public User addUser(User newUser){
        User user = new User(
                newUser.getName(),
                newUser.getEmail(),
                encoder.encode(newUser.getPassword()),
                newUser.getNeptun(),
                newUser.getBirthDate(),
                "ROLE_USER"
        );
        return repository.save(user);
    }

    public User updateUser(User updateUser, int id){
        User user = findById(id).get();
        if(!updateUser.getName().equals("")){
            user.setName(updateUser.getName());
        }
        if(!updateUser.getEmail().equals("")){
            user.setEmail(updateUser.getEmail());
        }
        if(!updateUser.getNeptun().equals("")){
            user.setNeptun(updateUser.getNeptun());
        }
        if(updateUser.getBirthDate()!=null){
            user.setBirthDate(updateUser.getBirthDate());
        }

        return repository.save(user);
    }

    public boolean existsByEmail(User user){
        return repository.existsByEmail(user.getEmail());
    }
}
