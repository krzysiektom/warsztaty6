package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.tweet.id =?1 order by c.created DESC ")
    List<Comment> getAllByTweetIdOrderByCreatedDesc(Long id);
}
