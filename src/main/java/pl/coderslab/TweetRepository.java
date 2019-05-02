package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query("SELECT t FROM Tweet t WHERE t.user.id=?1 ORDER BY t.created DESC")
    List<Tweet> getAllByUserIdOrderByCreatedDesc(Long id);
    @Query("SELECT t FROM Tweet t ORDER BY t.created DESC")
    List<Tweet> getAllOrderByCreatedDesc();
}
