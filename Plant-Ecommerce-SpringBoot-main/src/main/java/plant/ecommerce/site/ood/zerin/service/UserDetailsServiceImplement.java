package plant.ecommerce.site.ood.zerin.service;

import plant.ecommerce.site.ood.zerin.config.MyUserDetails;
import plant.ecommerce.site.ood.zerin.model.User;
import plant.ecommerce.site.ood.zerin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    public User getByUserEmail(String email) {
        return userRepository.getUserByUserName(email);
    }
}
