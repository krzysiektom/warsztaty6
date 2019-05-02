package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/all")
    public String showAllUsers() {
        return "allUsers";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "formUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") @Validated({ValidationUser.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formUser";
        }
        if (userService.isNotExistEmail(user)) {
            userRepository.save(user);
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Użytkownik o takim emailu już istnieje");
            return "formUser";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "formUser";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user") @Validated({ValidationUser.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formUser";
        }
        if (userService.validateEditUser(user)) {
            userRepository.save(user);
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Użytkownik o takim emailu już istnieje");
            return "formUser";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.delete(id);
        return "redirect:/user/all";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Validated({ValidationLogin.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "loginPage";
        }
        if (userService.validateUser(user)) {
            return "redirect:/tweet/main";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Błędny login lub hasło");
            return "loginPage";
        }
    }

    @GetMapping("/page")
    public String userPage(){
        return "userPage";
    }
}
