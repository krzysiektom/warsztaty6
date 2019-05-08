package pl.coderslab.tweet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;
import pl.coderslab.comment.Comment;
import pl.coderslab.comment.CommentRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/tweet")
public class TweetController {
    private final TweetRepository tweetRepository;

    private final AuthHandler authHandler;

    private final CommentRepository commentRepository;

    public TweetController(TweetRepository tweetRepository, AuthHandler authHandler, CommentRepository commentRepository) {
        this.tweetRepository = tweetRepository;
        this.authHandler = authHandler;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/all")
    public String showAllTweets(Model model) {
        model.addAttribute("allTweets", tweetRepository.getAllByOrderByCreatedDesc());
        return "allTweets";
    }

    @GetMapping("/main")
    public String addForm(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("allTweets", tweetRepository.getAllByOrderByCreatedDesc());
            Tweet tweet = new Tweet();
            model.addAttribute(tweet);
            return "mainPage";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/main")
    public String addTweet(@ModelAttribute("tweet") @Valid Tweet tweet, BindingResult result, Model model) {
        if (authHandler.isLogged()) {
            if (result.hasErrors()) {
                model.addAttribute("allTweets", tweetRepository.getAllByOrderByCreatedDesc());
                return "mainPage";
            }
            tweet.setUser(authHandler.getUser());
            tweetRepository.save(tweet);
            return "redirect:/tweet/main";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}")
    public String tweetDetails(@PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            Tweet tweet = tweetRepository.findOne(id);
            model.addAttribute("tweet", tweet);
            Comment comment = new Comment();
            model.addAttribute(comment);
            model.addAttribute("allComments", commentRepository.getAllByTweetOrderByCreatedDesc(tweet));
            return "tweetPage";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{id}")
    public String addComment(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, @PathVariable Long id, Model model) {
        if (authHandler.isLogged()) {
            Tweet tweet = tweetRepository.findOne(id);
            if (result.hasErrors()) {
                model.addAttribute("tweet", tweet);
                model.addAttribute("allComments", commentRepository.getAllByTweetOrderByCreatedDesc(tweet));
                return "tweetPage";
            } else {
                comment.setTweet(tweet);
                comment.setUser(authHandler.getUser());
                commentRepository.save(comment);
                return "redirect:/tweet/{id}";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/user")
    public String userTweets(Model model) {
        if (authHandler.isLogged()) {
            model.addAttribute("userTweets", tweetRepository.findAllByUserOrderByCreatedDesc(authHandler.getUser()));
            model.addAttribute("commentRepository", commentRepository);
            return "userTweets";
        } else {
            return "redirect:/";
        }
    }
}
