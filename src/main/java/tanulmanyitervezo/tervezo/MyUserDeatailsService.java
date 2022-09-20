package tanulmanyitervezo.tervezo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.Repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDeatailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<User> user =  userRepository.findByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+email));
        return user.map(MyUserDetails::new).get();
    }
}
