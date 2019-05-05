package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    private static final int LENGTH_SHORT_MESSAGES = 30;

    public List<Message> getAllShortMessagesBySenderOrderByCreatedDesc(User user) {
        List<Message> messages = messageRepository.getAllBySenderOrderByCreatedDesc(user);
        for (Message message : messages) {
            if (message.getText().length() > LENGTH_SHORT_MESSAGES - 3) {
                message.setText(message.getText().substring(0, 27).concat("..."));
            }
        }
        return messages;
    }
    public List<Message> getAllShortMessagesByReceiverOrderByCreatedDesc(User user) {
        List<Message> messages = messageRepository.getAllByReceiverOrderByCreatedDesc(user);
        for (Message message : messages) {
            if (message.getText().length() > LENGTH_SHORT_MESSAGES - 3) {
                message.setText(message.getText().substring(0, 27).concat("..."));
            }
        }
        return messages;
    }
}
