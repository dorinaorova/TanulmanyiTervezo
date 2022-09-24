package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody User requestUser) throws Exception{
        Authentication authObject;
        Optional<User> user;
        ResponseEntity response;
        try {
            authObject = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getEmail(), requestUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authObject);
            user = userService.findByEmail(requestUser.getEmail());
            response=  new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(BadCredentialsException e) {
            response=  new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
