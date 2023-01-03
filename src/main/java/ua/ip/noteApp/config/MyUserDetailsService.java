package ua.ip.noteApp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.ip.noteApp.account.UserDAO;
import ua.ip.noteApp.account.UserRepository;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = repository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not exists", username)));
        return new UserPrincipal(user);
    }
}
