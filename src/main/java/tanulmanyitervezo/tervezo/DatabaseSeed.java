package tanulmanyitervezo.tervezo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.SemesterRepository;
import tanulmanyitervezo.tervezo.repository.UserRepository;
import tanulmanyitervezo.tervezo.services.SemesterService;
import tanulmanyitervezo.tervezo.services.UserService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    SemesterService semesterService;

    @Override
    public void run(String... args) throws Exception{
        loadAdminData();
        loadCurrentSemester();
    }

    private void loadAdminData(){
        if(userRepository.findAll().size()==0 || !findAdmin()){
            User admin = new User();
            admin.setRoles("ROLE_ADMIN");
            admin.setNeptun("ADMIN");
            admin.setEmail("admin");
            admin.setPassword("admin");
            admin.setName("Admin");
            userService.addUser(admin);
        }
    }

    private boolean findAdmin(){
        List<User> users= userRepository.findAll();
        for (User u: users) {
            if(u.getRoles().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }

    private void loadCurrentSemester(){
        if(semesterRepository.findAll().size()==0){
            Semester current = new Semester();
            current.setName("2022-2023/1");
            current.setStart(new Date(2022-1900, Calendar.SEPTEMBER, 1).getTime());
            current.setEnd(new Date(2023-1900, Calendar.JANUARY, 31).getTime());
            current.setCurrent(true);
            semesterService.addSemester(current);
        }
        else if(semesterService.findCurrent()==null){
            List<Semester> semesters = semesterRepository.findAll();
            Collections.sort(semesters);
            semesterService.setCurrent(semesters.get(0).getId());
        }
    }
}
