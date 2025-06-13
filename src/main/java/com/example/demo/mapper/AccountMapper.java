package com.example.demo.mapper;



import com.example.demo.DTO.AccountsDTO;
import com.example.demo.entity.Accounts;

public class AccountMapper {
    public static Accounts toEntity(AccountsDTO dto) {
        Accounts account = new Accounts();
        account.setTitle(dto.title());
        return account;
    }

    public static AccountsDTO toDto(Accounts account) {
        return new AccountsDTO(account.getTitle());
    }

}
