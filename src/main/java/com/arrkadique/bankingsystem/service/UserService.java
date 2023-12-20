package com.arrkadique.bankingsystem.service;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.repository.UserHistoryRepository;
import com.arrkadique.bankingsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    final UserRepository userRepository;
    final UserHistoryRepository userHistoryRepository;

    public UserService(UserRepository usersRepository, UserHistoryRepository userHistoryRepository) {
        this.userRepository = usersRepository;
        this.userHistoryRepository = userHistoryRepository;
    }


    public User createUser(User users){
        return userRepository.save(users);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User changeUserPassword(Long userId, String password){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("No user with this id"));
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User changeUserPhoneNumber(Long userId, String phoneNumber){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("No user with this id"));
        user.setPhoneNumber(phoneNumber);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Please make sure you are using a valid email or password");
        }
        return user;
    }
}
