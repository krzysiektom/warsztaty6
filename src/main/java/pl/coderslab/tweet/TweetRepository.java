package pl.coderslab.tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.user.User;

import java.util.List;

interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByUserOrderByCreatedDesc(User user);

    List<Tweet> getAllByOrderByCreatedDesc();
}
