package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    private UserService userService;

    private AuthHandler authHandler;

    public UserController(UserRepository userRepository, UserService userService, AuthHandler authHandler) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authHandler = authHandler;
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
            return "redirect:/tweet/main";
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
            return "redirect:/";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                return "formUser";
            }
            if (userService.isNotExistAnotherUserWithEmail(user)) {
                user.setId(authHandler.getId());
                userRepository.save(user);
                userService.setSession(user);
                return "redirect:/tweet/main";
            } else {
                model.addAttribute("error", true);
                model.addAttribute("errorMsg", "Użytkownik o takim emailu już istnieje");
                return "formUser";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String deleteUser() {
        if (authHandler.isLogged()) {
            userRepository.delete(authHandler.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        authHandler.setLogged(false);
        return "redirect:/";
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
            return "redirect:/";
        }
    }
}
