package com.example.r2dbcuserdemo.dto;

import com.example.r2dbcuserdemo.entity.Users;
import com.example.r2dbcuserdemo.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(Users user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public static Users toEntity(UserDto userDto){
        Users user = new Users();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }


    public static UserTransaction toTransactionEntity(TransactionRequestDto dto){
        UserTransaction userTraction = new UserTransaction();
        userTraction.setUserId(dto.getUserId());
        userTraction.setAmount(dto.getAmount());
        userTraction.setTransactionDate(LocalDateTime.now());
        return userTraction;
    }

    public static TransactionResponseDto toTransactionResponseDto(TransactionRequestDto dto,TransactionStatus status){

        TransactionResponseDto responseDto = new TransactionResponseDto();

        responseDto.setAmount(dto.getAmount());
        responseDto.setUserId(dto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
    }
}
