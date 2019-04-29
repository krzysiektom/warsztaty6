package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    boolean validateUser(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null != user && userValidate.getPassword().equals(user.getPassword());
    }

    boolean isNotExistEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        System.out.println(user);
        return null == user;
    }
}
