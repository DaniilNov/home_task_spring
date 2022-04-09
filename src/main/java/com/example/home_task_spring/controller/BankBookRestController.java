package com.example.home_task_spring.controller;

import com.example.home_task_spring.model.BankBookDto;
import com.example.home_task_spring.service.BankBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookRestController {
    private final BankBookService bankBookService;

    public BankBookRestController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getAllBankBookByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(bankBookService.getAllBankBookByUser(id));
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getUserById(@PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.getById(bankBookId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.create(bankBookDto));
    }

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
