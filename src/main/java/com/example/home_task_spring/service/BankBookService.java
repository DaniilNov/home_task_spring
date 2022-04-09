package com.example.home_task_spring.service;

import com.example.home_task_spring.model.BankBookDto;

import java.util.List;

public interface BankBookService {
    List<BankBookDto> getAllBankBookByUser(Integer id);

    BankBookDto getById(Integer id);

    BankBookDto create(BankBookDto bankBookDto);

    BankBookDto update(BankBookDto bankBookDto);

    void delete(Integer id);

    void deleteBankBookByUserId(Integer userId);

}
