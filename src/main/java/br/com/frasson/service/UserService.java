package br.com.frasson.service;

import br.com.frasson.exception.WrongCredentialsException;
import br.com.frasson.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.frasson.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void login(String email, String password) {
        User registeredUser = userRepository.findByEmail(email);

        if(registeredUser == null) {
            throw new WrongCredentialsException("Email not found");
        }

        if(!passwordEncoder.matches(password, registeredUser.getPassword())) {
            throw new WrongCredentialsException("Password incorrect");
        }
    }

    public void register(String name, String email, String password) {
        userRepository.save(new User(name, email, passwordEncoder.encode(password)));
    }
}
