package ru.javaprojects.usersserver.web.service;

import org.springframework.stereotype.Service;
import ru.javaprojects.usersserver.model.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private Map<String, User> usersStorage = new HashMap<>();

    @PostConstruct
    private void fillUsersStorage() {
        User user1 = new User("John", "Doe", "john@gmail.com", "johnny", UUID.randomUUID().toString());
        User user2 = new User("Sarah", "Connor", "sarah@gmail.com", "sarah777", UUID.randomUUID().toString());
        User user3 = new User("Jack", "Daniels", "jack@gmail.com", "jacky", UUID.randomUUID().toString());
        usersStorage.put(user1.getEmail(), user1);
        usersStorage.put(user2.getEmail(), user2);
        usersStorage.put(user3.getEmail(), user3);
    }

    public User getUserByEmail(String email) {
        return usersStorage.get(email);
    }
}
