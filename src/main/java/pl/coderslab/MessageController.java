package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    private MessageRepository messageRepository;

    private AuthHandler authHandler;

    private UserRepository userRepository;

    public MessageController(MessageRepository messageRepository, AuthHandler authHandler, UserRepository userRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.authHandler = authHandler;
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String userMessages(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            model.addAttribute("senderMessages", messageService.getAllShortMessagesBySenderOrderByCreatedDesc(authHandler.getUser()));
            model.addAttribute("receiverMessages", messageService.getAllShortMessagesByReceiverOrderByCreatedDesc(authHandler.getUser()));
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
    public String messageDetails(@PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            Message message = messageRepository.findOne(id);
            if (message.getSender().getId().equals(authHandler.getId()) || message.getReceiver().getId().equals(authHandler.getId())) {
                message.setRead(true);
                messageRepository.save(message);
                model.addAttribute("message", message);
                return "messagePage";
            } else {
                return "redirect:/message/";
            }
        } else {
            return "redirect:/tweet/all";
        }
    }
}
