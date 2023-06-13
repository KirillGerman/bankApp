package ru.meshgroup.bankApplication.service;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.model.User;
import ru.meshgroup.bankApplication.repository.UserRepository;
import ru.meshgroup.bankApplication.security.CustomUserDetails;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final static String TEL_PATTERN = "^[0-9]{13}$";
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> user = Pattern.matches(TEL_PATTERN, name) ? userRepository.findUserByPhone(name) :
                EmailValidator.getInstance().isValid(name) ? userRepository.findUserByEmail(name) : null;

        return new CustomUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("Unknown user: " + name)));
    }
}
