package com.example.demo;

import com.example.demo.model.Subscribers;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    @Async("asyncExecutor")
    CompletableFuture<Subscribers> SaveSubscriber(Subscribers user);
//    CompletableFuture<Subscribers> updateUser(Subscribers user);
    @Async("asyncExecutor")
    CompletableFuture<Subscribers> getUserById(int id);

//    CompletableFuture<List<Subscribers>> getAllUsers();
    @Async("asyncExecutor")
    CompletableFuture<Void> deleteUser(int id);
}
