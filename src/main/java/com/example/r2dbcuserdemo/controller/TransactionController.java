package com.example.r2dbcuserdemo.controller;

import com.example.r2dbcuserdemo.dto.TransactionRequestDto;
import com.example.r2dbcuserdemo.dto.TransactionResponseDto;
import com.example.r2dbcuserdemo.service.UserTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final UserTransactionService service;

    @PostMapping
    public Mono<TransactionResponseDto> create(@RequestBody TransactionRequestDto requestDto){

        return service.createTransaction(requestDto);
    }
}
