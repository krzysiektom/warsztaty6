package pl.coderslab.message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;
import pl.coderslab.user.UserService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    private final AuthHandler authHandler;

    private final UserService userService;

    public MessageController(AuthHandler authHandler, UserService userService, MessageService messageService) {
        this.authHandler = authHandler;
        this.userService = userService;
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
            return "redirect:/";
        }
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        if (authHandler.isLogged()) {
            Message message = new Message();
            model.addAttribute(message);
            model.addAttribute("receiverUsers", userService.findAllWithOutUser(authHandler.getUser()));
            return "formMessage";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String addMessage(@ModelAttribute("message") @Valid Message message, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("receiverUsers", userService.findAllWithOutUser(authHandler.getUser()));
                return "formMessage";
            }
            message.setSender(authHandler.getUser());
            messageService.save(message);
            return "redirect:/message/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}")
    public String messageDetails(@PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            Message message = messageService.findOne(id);
            if (message.getSender().getId().equals(authHandler.getId()) || message.getReceiver().getId().equals(authHandler.getId())) {
                if (message.getReceiver().getId().equals(authHandler.getId())) {
                    message.setRead(true);
                    messageService.save(message);
                }
                model.addAttribute("message", message);
                return "messagePage";
            } else {
                return "redirect:/message/";
            }
        } else {
            return "redirect:/";
        }
    }
}
