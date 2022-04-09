package com.example.home_task_spring.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    Integer id;

    Integer userId;

    String number;

    BigDecimal amount;

    String currency;
}
