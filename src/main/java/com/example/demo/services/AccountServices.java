package com.example.demo.services;

import com.example.demo.DTO.AccountsDTO;
import java.util.List;

public interface AccountServices {
    AccountsDTO Create(AccountsDTO accountsDTO);
    AccountsDTO findById(Long id);
    List<AccountsDTO> getAll();
    AccountsDTO update(Long id, AccountsDTO accountsDTO);
    void delete(Long id);
}