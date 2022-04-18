package com.example.home_task_spring.controller;

import com.example.home_task_spring.model.BankBookDto;
import com.example.home_task_spring.service.BankBookService;
import com.example.home_task_spring.validation.Create;
import com.example.home_task_spring.validation.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/bank-book")
public class BankBookRestController {
    private final BankBookService bankBookService;

    public BankBookRestController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getAllBankBookByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(bankBookService.getAllBankBookByUser(userId));
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getUserById(@Min(value = 3L, message = "Больше 3") @PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.getById(bankBookId));
    }

    @Validated(Create.class)
    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@Valid @RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.create(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.update(bankBookDto);
    }

    @DeleteMapping("/{bankBookId}")
    public void deleteBankBook(@PathVariable Integer id) {
        bankBookService.delete(id);
    }

    @DeleteMapping("/by-user-id/{userId}")
    public void deleteBankBookByUserId(@PathVariable Integer id) {
        bankBookService.deleteBankBookByUserId(id);
    }

}
