package com.example.home_task_spring.model;

import com.example.home_task_spring.validation.Create;
import com.example.home_task_spring.validation.Currency;
import com.example.home_task_spring.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    Integer id;

    Integer userId;

    @NotBlank
    String number;

    @Min(value = 0L)
    BigDecimal amount;

    @Currency
    String currency;
}
