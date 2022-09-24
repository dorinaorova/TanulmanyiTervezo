package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("id") int id){
        Optional<User> user = service.findById(id);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        if(newUser.getRoles()==null){
            newUser.setRoles("ROLE_USER");
        }
        newUser.setActive(true);
        User user = service.addUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
