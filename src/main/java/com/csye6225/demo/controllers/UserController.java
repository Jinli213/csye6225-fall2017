package com.csye6225.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.Account;
import com.csye6225.demo.pojo.AccountRepository;

import java.util.List;


@Controller
public class UserController {
    @Autowired(required = false)
    private AccountRepository accountRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody Account account) {

        Account a = new Account();
        boolean flag = false;
        List<Account> users = (List<Account>) accountRepository.findAll();
        for(Account user : users){
            if (account.getEmail().equals(user.getEmail())){
                flag =true;
                break;
            }
        }
        if(flag){
            return "account has already existed";
        }
        else{
            String pw_hash = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
            System.out.println(pw_hash);

            a.setEmail(account.getEmail());
            a.setPassword(pw_hash);
            accountRepository.save(a);
        }
            return "added successfully";


    }
}

