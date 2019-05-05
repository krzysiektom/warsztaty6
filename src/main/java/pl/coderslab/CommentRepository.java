package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comments WHERE tweet_id = ?1 ORDER BY created DESC", nativeQuery = true)
    List<Comment> getAllByTweetIdOrderByCreatedDesc(Long id);
}
