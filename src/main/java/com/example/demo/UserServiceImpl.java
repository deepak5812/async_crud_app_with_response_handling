package com.example.demo;

import com.example.demo.model.Subscribers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

     @Autowired
     UserRepository userRepository;

    @Override
    public CompletableFuture<Subscribers> SaveSubscriber(Subscribers subscribers) {
        System.out.println(Thread.currentThread().getId()+"-----------------"+subscribers.getMsisdn()+"------------------"+System.currentTimeMillis()+"-------------inside services");

        System.out.println("inside service method ------------------------------------------"+subscribers.getMsisdn());
        Subscribers savedUser = userRepository.save(subscribers);
        return CompletableFuture.completedFuture(savedUser);
    }


    @Override
    public CompletableFuture<Subscribers> getUserById(int id) {

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Subscribers getUser = userRepository.findById(id).get();

        return CompletableFuture.completedFuture(getUser);
//        return CompletableFuture.supplyAsync(() -> userRepository.findById(id).orElse(null));
    }

    @Override
    public CompletableFuture<Void> deleteUser(int id) {
        System.out.println(Thread.currentThread().getId()+"-----------------------------------"+System.currentTimeMillis()+"-------------inside services");
        try {
            Thread.sleep(100000);
            userRepository.deleteById(id);
            return CompletableFuture.completedFuture(null);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}