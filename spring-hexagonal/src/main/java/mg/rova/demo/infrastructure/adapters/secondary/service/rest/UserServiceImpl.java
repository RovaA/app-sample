package mg.rova.demo.infrastructure.adapters.secondary.service.rest;

import mg.rova.demo.domain.ports.primary.UserService;
import mg.rova.demo.infrastructure.adapters.secondary.repository.UserRepository;
import mg.rova.demo.infrastructure.adapters.secondary.service.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mg.rova.demo.domain.entities.User;

@Service
public class UserServiceImpl extends AbsServiceImpl<User, Long, UserRepository> implements UserService {

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super(repository);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		return new UserDetailsImpl(user);
	}

	@Override
	public User create(User user) {
		return this.save(user);
	}
}
