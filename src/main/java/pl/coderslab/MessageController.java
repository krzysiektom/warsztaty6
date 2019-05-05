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
    final
    MessageRepository messageRepository;

    final
    AuthHandler authHandler;

    final
    UserRepository userRepository;

    public MessageController(MessageRepository messageRepository, AuthHandler authHandler, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.authHandler = authHandler;
        this.userRepository = userRepository;
    }

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
            model.addAttribute("receiverUsers", userRepository.findAllWithOutUser(authHandler.getUser()));

            return "formMessage";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/add")
    public String addMessage(@ModelAttribute("message") @Valid Message message, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("receiverUsers", userRepository.findAllWithOutUser(authHandler.getUser()));
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
        Message message=messageRepository.findOne(id);
        message.setRead(true);
        messageRepository.save(message);
        model.addAttribute("message",message);
        return "messagePage";

    }
}
