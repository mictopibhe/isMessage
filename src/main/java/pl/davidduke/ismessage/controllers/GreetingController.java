package pl.davidduke.ismessage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.davidduke.ismessage.entity.Message;
import pl.davidduke.ismessage.repository.MessageRepository;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam String text,
                             @RequestParam String tag,
                             Model model) {

        if (text.trim().isBlank() || tag.trim().isBlank()) {
            model.addAttribute("error", "Both text and tag fields must be filled out.");
            return "main";
        }

        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }


//    @GetMapping("/main")
//    public String main(Map<String, Object> model) {
//        Iterable<Message> messages = messageRepository.findAll();
//        model.put("messages", messages);
//        return "main";
//    }
//
//    @PostMapping("/filter")
//    public String filter (@RequestParam String filter, Map<String, Object> model) {
//        Iterable<Message> messages;
//        if (Objects.nonNull(filter) && !filter.isEmpty()) {
//            messages = messageRepository.findByTag(filter);
//        } else {
//            messages = messageRepository.findAll();
//        }
//
//        model.put("messages", messages);
//        return "main";
//    }
}
