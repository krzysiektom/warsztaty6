package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tweet")
public class TweetController {
    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    AuthHandler authHandler;

    @Autowired
    UserRepository userRepository;

    @ModelAttribute(name = "userName", value = "userName")
    public String userName() {
        return authHandler.getName();
    }

    @ModelAttribute("allTweets")
    public List<Tweet> getAllTweets() {
        return tweetRepository.getAllOrderByCreatedDesc();
    }

    @ModelAttribute("userAllTweets")
    public List<Tweet> getUserAllTweets() {
        return tweetRepository.getAllByUserIdOrderByCreatedDesc(1L);
    }

    @GetMapping("/all")
    public String showAllTweets() {
        return "allTweets";
    }

    @GetMapping("/user")
    public String showUserAllTweets() {
        return "userTweets";
    }

    @GetMapping("/main")
    public String addForm(Model model) {
        if (authHandler.isLogged()) {
            Tweet tweet = new Tweet();
            model.addAttribute(tweet);
            return "mainPage";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/main")
    public String addTweet(@ModelAttribute("tweet") @Valid Tweet tweet, BindingResult result) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                return "mainPage";
            }
            tweet.setUser(userRepository.findOne(authHandler.getId()));
            tweetRepository.save(tweet);
            return "redirect:/tweet/main";
        } else {
            return "redirect:/tweet/all";
        }
    }
}
