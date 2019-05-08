package pl.coderslab.message;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.user.User;

import java.util.List;

interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getAllBySenderOrderByCreatedDesc(User user);

    List<Message> getAllByReceiverOrderByCreatedDesc(User user);
}
