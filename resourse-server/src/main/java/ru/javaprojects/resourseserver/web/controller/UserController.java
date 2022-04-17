package ru.javaprojects.resourseserver.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.javaprojects.resourseserver.web.model.User;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private Environment environment;

    @GetMapping("/status/check")
    public String checkStatus() {
        return "Status is UP, port is " + environment.getProperty("local.server.port");
    }

    @DeleteMapping("/{id}")
//    @Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id=" + id;
    }

    @GetMapping("/{id}")
    @PostAuthorize("returnObject.userId == #jwt.subject")
    public User getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new User("Igor", "S", "dfdfgdf-fdgdfg-53453-gfhgfhf");
    }
}
