package com.example.r2dbcuserdemo.service;

import com.example.r2dbcuserdemo.dto.EntityDtoUtil;
import com.example.r2dbcuserdemo.dto.TransactionRequestDto;
import com.example.r2dbcuserdemo.dto.TransactionResponseDto;
import com.example.r2dbcuserdemo.dto.TransactionStatus;
import com.example.r2dbcuserdemo.repository.UserRepository;
import com.example.r2dbcuserdemo.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTransactionService {

    private final UserRepository userRepository;

    private final UserTransactionRepository  transactionRepository;


    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
        System.out.println("amount = "+requestDto.getAmount());
        System.out.println("user id = "+requestDto.getUserId());
        return userRepository
                .updateUserBalance(requestDto.getUserId(),requestDto.getAmount())
                .filter(b->{
                    System.out.println(Boolean.valueOf(b));
                   return Boolean.valueOf(b);
                }).map(b->EntityDtoUtil.toTransactionEntity(requestDto))
                .flatMap(transactionRepository::save)
                .map(ut->EntityDtoUtil.toTransactionResponseDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toTransactionResponseDto(requestDto,TransactionStatus.DECLINED));
    }


}
