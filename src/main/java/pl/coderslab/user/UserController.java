package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final AuthHandler authHandler;

    public UserController(UserService userService, AuthHandler authHandler) {
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
            userService.save(user);
            userService.setSession(user);
            return "redirect:/tweet/main";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "User with this email exists");
            return "formUser";
        }
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        if (authHandler.isLogged()) {
            User user = authHandler.getUser();
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
                userService.save(user);
                userService.setSession(user);
                return "redirect:/tweet/main";
            } else {
                model.addAttribute("error", true);
                model.addAttribute("errorMsg", "User with this email exists");
                return "formUser";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String deleteUser() {
        if (authHandler.isLogged()) {
            userService.delete(authHandler.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        authHandler.setLogged(false);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.validateUserAndSetSession(email, password)) {
            return "redirect:/tweet/main";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Wrong login or password");
            return "loginPage";
        }
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
