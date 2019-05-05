package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AuthHandler authHandler;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String userMessages(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("senderMessages", messageRepository.getAllBySenderOrderByCreatedDesc(authHandler.getUser()));
            model.addAttribute("receiverMessages", messageRepository.getAllByReceiverOrderByCreatedDesc(authHandler.getUser()));
            return "userMessages";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        if (authHandler.isLogged()) {
            Message message = new Message();
            model.addAttribute(message);
            model.addAttribute("receiverUsers", userRepository.findAll());

            return "formMessage";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/add")
    public String addMessage(@ModelAttribute("message") @Valid Message message, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("receiverUsers", userRepository.findAll().remove(authHandler.getUser()));
                return "formMessage";
            }
            message.setSender(authHandler.getUser());
            messageRepository.save(message);
            return "redirect:/message/";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @GetMapping("/{id}")
    public String messageDetails(@PathVariable Long id, Model model){
        model.addAttribute("message",messageRepository.findOne(id));
        return "messagePage";

    }
}
