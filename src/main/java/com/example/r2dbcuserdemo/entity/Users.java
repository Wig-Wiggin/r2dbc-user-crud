package com.example.r2dbcuserdemo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString

public class Users {

    @Id
    private Integer id;

    private String name;

    private Integer balance;
}
