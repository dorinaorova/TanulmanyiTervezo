package tanulmanyitervezo.tervezo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.Repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository repository;
    @Autowired
    AuthenticationManager authenticationManager;
//
//    public AuthController(AuthenticationManager authenticationManager){
//        this.authenticationManager = authenticationManager;
//    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody User user) throws Exception{
        Authentication authObject;
        try {
            authObject = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authObject);
        }
        catch(BadCredentialsException e) {
            throw new Exception("NEMJÓ");
        }

            return new ResponseEntity<>(HttpStatus.OK);
    }
}
