package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthHandler authHandler;

    @ModelAttribute(name = "userName", value = "userName")
    public String userName() {
        return authHandler.getName();
    }

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
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formUser";
        }
        if (userService.isNotExistEmail(user)) {
            userRepository.save(user);
            userService.setSession(user);
            return "redirect:/user/page";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Użytkownik o takim emailu już istnieje");
            return "formUser";
        }
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        if (authHandler.isLogged()) {
            User user = userRepository.findOne(authHandler.getId());
            model.addAttribute("user", user);
            return "formUser";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                return "formUser";
            }
            if (userService.validateEditUser(user)) {
                userRepository.save(user);
                userService.setSession(user);
                return "redirect:/";
            } else {
                model.addAttribute("error", true);
                model.addAttribute("errorMsg", "Użytkownik o takim emailu już istnieje");
                return "formUser";
            }
        } else {
            return "redirect:/tweet/all";
        }
    }

    @GetMapping("/delete")
    public String deleteUser() {
        if (authHandler.isLogged()) {
            userRepository.delete(authHandler.getId());
        }
        return "redirect:/tweet/all";
    }

    @GetMapping("/logout")
    public String logout() {
        authHandler.setLogged(false);
        return "redirect:/tweet/all";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.validateUserAndSetSession(email, password)) {
            return "redirect:/tweet/main";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Błędny login lub hasło");
            return "loginPage";
        }
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "loginPage";
    }

    @GetMapping("/page")
    public String userPage() {
        if (authHandler.isLogged()) {
            return "userPage";
        } else {
            return "redirect:/tweet/all";
        }
    }
}
