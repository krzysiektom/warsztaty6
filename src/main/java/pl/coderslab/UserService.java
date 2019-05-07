package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthHandler authHandler;

    boolean validateUserAndSetSession(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (null != user && BCrypt.checkpw(password, user.getPassword())) {
            setSession(user);
            return true;
        }
        return false;
    }

    void setSession(User user) {
        authHandler.setId(user.getId());
        authHandler.setLogged(true);
        authHandler.setName(user.getFirstName() + " " + user.getLastName());
    }

    boolean isNotExistEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user;
    }

    boolean isNotExistAnotherUserWithEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user || authHandler.getId().equals(user.getId());
    }
}
