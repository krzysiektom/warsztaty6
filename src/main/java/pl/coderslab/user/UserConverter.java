package pl.coderslab.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<String, User> {
    @Autowired
    UserRepository userRepository;

    @Override
    public User convert(String strings) {
        return userRepository.findOne(Long.parseLong(strings));
    }
}