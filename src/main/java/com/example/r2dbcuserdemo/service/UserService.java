package com.example.r2dbcuserdemo.service;

import com.example.r2dbcuserdemo.dto.EntityDtoUtil;
import com.example.r2dbcuserdemo.dto.UserDto;
import com.example.r2dbcuserdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Flux<UserDto> all(){
        return repository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(final int userId){
        return this.repository.findById(userId).map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> update(int id, Mono<UserDto> userDtoMono){
        return repository.findById(id)
                .flatMap(u->userDtoMono.map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setId(id))).flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> delete( int id){
        return repository.deleteById(id);
    }
}
