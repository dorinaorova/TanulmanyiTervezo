package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("id") int id){
        Optional<User> user = service.findById(id);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User updateUser){
        try {
            User updatedUser = service.updateUser(updateUser, id);
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("Nem található felhasználó!");
        }
    }
}
