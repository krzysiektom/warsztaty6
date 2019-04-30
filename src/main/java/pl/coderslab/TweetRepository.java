package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> getAllByIdOrderByCreatedDesc(Long id);
}
