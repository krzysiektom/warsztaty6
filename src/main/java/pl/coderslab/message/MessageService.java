package pl.coderslab.message;

import org.springframework.stereotype.Service;
import pl.coderslab.user.User;

import java.util.List;

@Service
class MessageService {

    private static final int LENGTH_SHORT_MESSAGES = 30;

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    List<Message> getAllShortMessagesBySenderOrderByCreatedDesc(User user) {
        List<Message> messages = messageRepository.getAllBySenderOrderByCreatedDesc(user);
        makeShortMessages(messages);
        return messages;
    }

    List<Message> getAllShortMessagesByReceiverOrderByCreatedDesc(User user) {
        List<Message> messages = messageRepository.getAllByReceiverOrderByCreatedDesc(user);
        makeShortMessages(messages);
        return messages;
    }

    private void makeShortMessages(List<Message> messages) {
        for (Message message : messages) {
            if (message.getText().length() > LENGTH_SHORT_MESSAGES - 3) {
                message.setText(message.getText().substring(0, 27).concat("..."));
            }
        }
    }

    void save(Message message) {
        messageRepository.save(message);
    }

    Message findOne(Long id) {
        return messageRepository.findOne(id);
    }
}
