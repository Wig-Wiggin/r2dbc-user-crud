package com.example.r2dbcuserdemo.controller;

import com.example.r2dbcuserdemo.dto.UserDto;
import com.example.r2dbcuserdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/all")
    public Flux<UserDto> all(){
        return service.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> byId(@PathVariable int id){
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDtoMono){
        return service.createUser(userDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id, @RequestBody Mono<UserDto> userDtoMono){
        return service.update(id,userDtoMono).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable int id){
        return service.delete(id);
    }

}
