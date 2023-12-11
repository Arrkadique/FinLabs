package com.arrkadique.bankingsystem.service;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.repository.UserHistoryRepository;
import com.arrkadique.bankingsystem.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    final UsersRepository usersRepository;
    final UserHistoryRepository userHistoryRepository;

    public UserService(UsersRepository usersRepository, UserHistoryRepository userHistoryRepository) {
        this.usersRepository = usersRepository;
        this.userHistoryRepository = userHistoryRepository;
    }


    public User createUser(User users){
        return usersRepository.save(users);
    }

    public void deleteUser(Long userId){
        usersRepository.deleteById(userId);
    }

    public User changeUserPassword(Long userId, String password){
        User user = usersRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("No user with this id"));
        user.setPassword(password);
        return usersRepository.save(user);
    }

    public User changeUserPhoneNumber(Long userId, String phoneNumber){
        User user = usersRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("No user with this id"));
        user.setPhoneNumber(phoneNumber);
        return usersRepository.save(user);
    }



}
