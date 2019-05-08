package pl.coderslab.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.tweet.Tweet;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByTweetOrderByCreatedDesc(Tweet tweet);

    Long countByTweet(Tweet tweet);
}
