package pl.davidduke.ismessage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.davidduke.ismessage.entity.Message;
import pl.davidduke.ismessage.entity.User;
import pl.davidduke.ismessage.repository.MessageRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MessageRepository msgRepository;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if (filter == null || filter.isEmpty()) {
            model.addAttribute("messages", msgRepository.findAll());
        } else {
            model.addAttribute("messages", msgRepository.findByTag(filter));
        }
        model.addAttribute("message", new Message());
        model.addAttribute("filter", "");
        return "main";
    }

    @PostMapping
    public String addMessage(@AuthenticationPrincipal User user,
                             @ModelAttribute("message") @Valid Message message, BindingResult bindingResult,
                             Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("messages", msgRepository.findAll());
            model.addAttribute("errors", errors);
            model.addAttribute("filter", "");
            return "main";
        }
        message.setAuthor(user);
        msgRepository.save(message);
        return "redirect:/";
    }
}


