package tanulmanyitervezo.tervezo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDeatailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<User> user =  userRepository.findByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+email));
        return user.map(MyUserDetails::new).get();
    }
}
