package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/all")
    public String showAllTweets(Model model) {
        model.addAttribute("allTweets", tweetRepository.getAllOrderByCreatedDesc());
        return "allTweets";
    }

    @GetMapping("/main")
    public String addForm(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("userName", authHandler.getName());
            model.addAttribute("allTweets", tweetRepository.getAllOrderByCreatedDesc());
            Tweet tweet = new Tweet();
            model.addAttribute(tweet);
            return "mainPage";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/main")
    public String addTweet(@ModelAttribute("tweet") @Valid Tweet tweet, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("userName", authHandler.getName());
                model.addAttribute("allTweets", tweetRepository.getAllOrderByCreatedDesc());
                return "mainPage";
            }
            tweet.setUser(userRepository.findOne(authHandler.getId()));
            tweetRepository.save(tweet);
            return "redirect:/tweet/main";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @GetMapping("/{id}")
    public String tweetDetails(@PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("tweet", tweetRepository.findOne(id));
            Comment comment = new Comment();
            model.addAttribute(comment);
            model.addAttribute("allComments", commentRepository.getAllByTweetIdOrderByCreatedDesc(id));
            return "tweetPage";
        } else {
            return "redirect:/tweet/all";
        }
    }

    @PostMapping("/{id}")
    public String addComment(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, @PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("tweet", tweetRepository.findOne(id));
                model.addAttribute("allComments", commentRepository.getAllByTweetIdOrderByCreatedDesc(id));
                return "tweetPage";
            } else {
                comment.setTweet(tweetRepository.findOne(id));
                comment.setUser(userRepository.findOne(authHandler.getId()));
                commentRepository.save(comment);
                return "redirect:/tweet/{id}";
            }
        } else {
            return "redirect:/tweet/all";
        }
    }

    @GetMapping("/user")
    public String userTweets(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("userTweets", tweetRepository.findAllByUserOrderByCreatedDesc(authHandler.getUser()));
            return "userTweets";
        } else {
            return "redirect:/tweet/all";
        }
    }
}
