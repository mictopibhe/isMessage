package pl.davidduke.ismessage.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.davidduke.ismessage.entity.Role;
import pl.davidduke.ismessage.entity.User;
import pl.davidduke.ismessage.repository.UserRepository;

import java.util.Collections;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("user") User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
