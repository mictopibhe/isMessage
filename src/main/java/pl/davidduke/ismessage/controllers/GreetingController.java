package pl.davidduke.ismessage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.davidduke.ismessage.entity.Message;
import pl.davidduke.ismessage.repository.MessageRepository;

@Controller
@RequestMapping("/")
public class GreetingController {
    @Autowired
    private MessageRepository msgRepository;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("messages", msgRepository.findAll());
        model.addAttribute("message", new Message());
        return "main";
    }

    @PostMapping
    public String addMessage(@ModelAttribute("message") @Valid Message message, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messages", msgRepository.findAll());
            return "main";
        }
        msgRepository.save(message);
        return "redirect:/";
    }

    @PostMapping("filter")
    public String messageFilter(@RequestParam String filter, Model model) {
        if (filter == null || filter.isEmpty()) {
            model.addAttribute("messages", msgRepository.findAll());
        } else {
            model.addAttribute("messages", msgRepository.findByTag(filter));
        }
        model.addAttribute("message", new Message());
        return "main";
    }
}


