package com.example.demo.services.impl;

import com.example.demo.DTO.AccountsDTO;
import com.example.demo.entity.Accounts;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountServices {
    private final AccountRepository accountRepository;
//    private final AccountMapper accountMapper;

    @Override
    public AccountsDTO Create(AccountsDTO accountsDTO) {
        Accounts account = AccountMapper.toEntity(accountsDTO);
        Accounts savedAccount = accountRepository.save(account);
        return AccountMapper.toDto(savedAccount);

    }

    @Override
    public AccountsDTO findById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<AccountsDTO> getAll() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountsDTO update(Long id, AccountsDTO accountsDTO) {
        Accounts existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existingAccount.setTitle(accountsDTO.title());
        return AccountMapper.toDto(accountRepository.save(existingAccount));
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}