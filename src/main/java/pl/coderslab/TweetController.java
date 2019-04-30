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

    //czasowo do momeentu potweierdzenia logowania
    @Autowired
    UserRepository userRepository;

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //usunąć

    @ModelAttribute("allTweets")
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @GetMapping("/all")
    public String showAllTweets() {
        return "allTweets";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        Tweet tweet = new Tweet();
        model.addAttribute(tweet);
        return "formTweet";
    }

    @PostMapping("/add")
    public String addTweet(@ModelAttribute("tweet") @Valid Tweet tweet, BindingResult result){
        if (result.hasErrors()){
            return "formTweet";
        }
        tweet.setUser(userRepository.findOne(tweet.getUser().getId()));

        tweetRepository.save(tweet);
        return "redirect:/tweet/all";
    }
}
