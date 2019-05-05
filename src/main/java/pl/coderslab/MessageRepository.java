package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> getAllBySenderOrderByCreatedDesc(User user);
    List<Message> getAllByReceiverOrderByCreatedDesc(User user);
}
