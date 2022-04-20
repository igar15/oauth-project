package ru.javaprojects.usersserver.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javaprojects.usersserver.model.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private Map<String, User> usersStorage = new HashMap<>();

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @PostConstruct
    private void fillUsersStorage() {
        User user1 = new User("John", "Doe", "john@gmail.com", "johnny", UUID.randomUUID().toString(), passwordEncoder.encode("111111"));
        User user2 = new User("Sarah", "Connor", "sarah@gmail.com", "sarah777", UUID.randomUUID().toString(), passwordEncoder.encode("222222"));
        User user3 = new User("Jack", "Daniels", "jack@gmail.com", "jacky", UUID.randomUUID().toString(), passwordEncoder.encode("333333"));
        usersStorage.put(user1.getEmail(), user1);
        usersStorage.put(user2.getEmail(), user2);
        usersStorage.put(user3.getEmail(), user3);
    }

    public User getUser(String email) {
        return usersStorage.get(email);
    }

    public User getUser(String email, String password) {
        User user = usersStorage.get(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
