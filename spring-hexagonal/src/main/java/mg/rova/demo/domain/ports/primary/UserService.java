package mg.rova.demo.domain.ports.primary;

import mg.rova.demo.domain.ports.primary.IsService;
import org.springframework.security.core.userdetails.UserDetailsService;

import mg.rova.demo.domain.entities.User;

public interface UserService extends IsService<User, Long>, UserDetailsService {

    User create(User user);
}
