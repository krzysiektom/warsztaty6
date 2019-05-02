package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> getAllByIdOrderByCreatedDesc(Long id);
    @Query("SELECT t FROM Tweet t ORDER BY t.created DESC")
    List<Tweet> getAllOrderByCreatedDesc();
}
