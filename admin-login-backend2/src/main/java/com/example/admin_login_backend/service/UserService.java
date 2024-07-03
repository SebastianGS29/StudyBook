package com.example.admin_login_backend.service;
import com.example.admin_login_backend.model.User;
import com.example.admin_login_backend.repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final FirebaseRepository firebaseRepository;

    @Autowired
    public UserService(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return firebaseRepository.getUsers();
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return firebaseRepository.getUserByEmail(email);
    }
    public List<String> getAllEmails() throws ExecutionException, InterruptedException {
        List<User> users = firebaseRepository.getUsers();
        return users.stream().map(User::getEmail).collect(Collectors.toList());
    }
    public void saveUser(User user) {
        firebaseRepository.saveUser(user);
    }
}