package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.ResponseModels.UserResponse;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.security.jwt.JwtUtils;
import tanulmanyitervezo.tervezo.security.service.MyUserDetails;
import tanulmanyitervezo.tervezo.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User requestUser) throws Exception{
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getEmail(), requestUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).get(0);

        return new ResponseEntity<>(new UserResponse(jwt, userDetails.getId(), role), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Hibás felhasználói adatok!");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){
        if (userService.existsByEmail(user)) {
            return ResponseEntity
                    .badRequest()
                    .body("Ez az emailcím már használatban van!");
        }

        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
