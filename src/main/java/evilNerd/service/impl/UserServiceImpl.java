package evilNerd.service.impl;

import evilNerd.domain.User;
import evilNerd.repository.UserRepository;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

 //   private final UserRepository userRepository;

    //1. Validation layer
    //2. Convert http request params into User object
    //3. Extended calls into DB or external services

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }
}
