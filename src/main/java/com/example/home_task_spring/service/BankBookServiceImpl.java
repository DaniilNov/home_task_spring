package com.example.home_task_spring.service;

import com.example.home_task_spring.model.BankBookDto;
import com.example.home_task_spring.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();
    private final Map<Integer, UserDto> userDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceIdBankBook = new AtomicInteger(1);

    @PostConstruct
    void init() {
        userDtoMap.put(1, UserDto.builder().id(1).name("User").build());
        UserDto userDto = UserDto.builder().id(2).name("User2").build();
        bankBookDtoMap.put(1, BankBookDto.builder().id(1).amount(BigDecimal.ONE).currency("USD").number("one").userId(userDto.getId()).build());
    }

    @Override
    public List<BankBookDto> getAllBankBookByUser(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new RuntimeException("Для данного пользователя BankBook не найден");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto getById(Integer id) {
        BankBookDto bankBookDto = bankBookDtoMap.get(id);
        if (bankBookDto == null) {
            throw new RuntimeException("Данный BankBook не найден");
        }
        return bankBookDto;

    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        List<BankBookDto> bankBookDtos = bankBookDtoMap.values().stream()
                .filter(e -> e.getUserId().equals(bankBookDto.getUserId()))
                .filter(e -> e.getAmount().equals(bankBookDto.getAmount()))
                .filter(e -> e.getCurrency().equals(bankBookDto.getCurrency()))
                .collect(Collectors.toList());

        if (!bankBookDtos.isEmpty()) {
            throw new RuntimeException("Счет с данной валютой уже имеется!");
        }

        Integer id = sequenceIdBankBook.getAndIncrement();
        bankBookDto.setId(id);
        bankBookDtoMap.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookDto bankBookDtoFromMap = bankBookDtoMap.get(bankBookDto.getId());
        if (bankBookDtoFromMap == null) {
            throw new RuntimeException("Лицевой счет не найден!");
        }
        if (!bankBookDtoFromMap.getNumber().equals(bankBookDto.getNumber())) {
            throw new RuntimeException("Лицевой счет менять нельзя!");
        }
        bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);
        return bankBookDto;
    }

    @Override
    public void delete(Integer id) {
        bankBookDtoMap.remove(id);
    }

    @Override
    public void deleteBankBookByUserId(Integer userId) {
        List<Integer> bankBookDtoRemoveId = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
                .map(BankBookDto::getId)
                .collect(Collectors.toList());

        for (Integer removeId : bankBookDtoRemoveId) {
            bankBookDtoMap.remove(removeId);
        }
    }
}
