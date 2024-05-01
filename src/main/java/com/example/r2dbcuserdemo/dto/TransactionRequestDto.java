package com.example.r2dbcuserdemo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class TransactionRequestDto {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("amount")
    private  Integer amount;
}
