package com.example.demo.mapper;

import com.example.demo.DTO.AccountsDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Accounts;
import com.example.demo.entity.Hobby;
import com.example.demo.entity.Passport;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper  {


    public static User toEntity(UserDTO dto, List<Hobby> hobbies){
        User user = new User();

        user.setName(dto.name());
        user.setAge(dto.age());

        Passport passport = new Passport();
        passport.setNumber(dto.passport().getNumber());
        user.setPassport(passport);

        if(dto.accounts() != null){
            List<Accounts> accountsList = dto.accounts().stream().map(acc ->{
                Accounts a = new Accounts();
                a.setTitle(acc.title());
                a.setUser(user);
                return a;

            }).collect(Collectors.toList());
            user.setAccounts(accountsList);
        }
        user.setHobbies(Set.copyOf(hobbies));
        return user;
    }

    public static UserDTO toDto(User user){
        List<AccountsDTO> accountsDTOS = user.getAccounts().stream().map(a -> new AccountsDTO(a.getTitle())).toList();

        Set<String> hobbyNames = user.getHobbies().stream().map(Hobby::getType).collect(Collectors.toSet());

        return new UserDTO(
                user.getName(),
                user.getAge(),
                user.getPassport(),
                accountsDTOS,
                hobbyNames

        );
    }
}
