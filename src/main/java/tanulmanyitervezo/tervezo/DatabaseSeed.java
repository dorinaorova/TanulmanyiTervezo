package tanulmanyitervezo.tervezo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.UserRepository;

import java.util.List;

@Component
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        loadAdminData();
    }

    private void loadAdminData(){
        if(userRepository.count()==0 || !findAdmin()){
            User admin = new User();
            admin.setRoles("ROLE_ADMIN");
            admin.setNeptun("ADMIN");
            admin.setEmail("admin@admin.com");
            admin.setPassword("admin");
            admin.setName("Admin");
            admin.setActive(true);
            userRepository.save(admin);
        }
    }

    private boolean findAdmin(){
        List<User> users= userRepository.findAll();
        for (User u: users) {
            if(u.getRoles().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }
}
