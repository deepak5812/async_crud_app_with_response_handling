package com.example.demo;


import com.example.demo.model.Subscribers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.SocketOption;
import java.net.URI;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/changeMode")
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<Subscribers>> SaveSubscriber(@RequestBody Subscribers subscribers) {
        System.out.println(Thread.currentThread().getId()+"-----------------"+subscribers.getMsisdn()+"------------------"+System.currentTimeMillis()+"-------------inside services");
        CompletableFuture<Subscribers> future = userService.SaveSubscriber(subscribers);
        return future.thenApply(result -> ResponseEntity.ok(result))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @GetMapping("/getMode/{id}")
    public CompletableFuture<ResponseEntity<?>> getUserById(@PathVariable int id) {
        CompletableFuture<Subscribers> futureGET =  userService.getUserById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return futureGET.thenApplyAsync(user -> {
            if (user != null) {
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @DeleteMapping("/deleteMode/{id}")
    public CompletableFuture<ResponseEntity<Object>> deleteUser(@PathVariable int id) {
        System.out.println(Thread.currentThread().getId()+"-----------------------------------"+System.currentTimeMillis()+"----------"+"inside controller");
        return userService.deleteUser(id)
                .thenApply(user -> ResponseEntity.ok().build())
                .exceptionally(ex -> {
                    if (ex.getCause() instanceof EntityNotFoundException) {
                        return ResponseEntity.notFound().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
//         return userService.deleteUser(id)
//                .thenApply(user -> ResponseEntity.ok().build())
//                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
////        return userService.deleteUser(id)
//                .thenApply(user -> {
//                    if(user != false) {
//                        return ResponseEntity.ok().build();
//                    } else {
//                        return ResponseEntity.notFound().build();
//                    }
//                })
//                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}