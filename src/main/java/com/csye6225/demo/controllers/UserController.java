package com.csye6225.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.User;
import com.csye6225.demo.pojo.UserRepository;

import java.util.List;


@Controller
public class UserController {
    @Autowired(required = false)
    private UserRepository userRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user) {

        User u = new User();
        boolean flag = false;
        List<User> users = (List<User>) userRepository.findAll();
        for(User user1 : users){
            if (user.getEmail().equals(user1.getEmail())){
                flag =true;
                break;
            }
        }
        if(flag){
            return "account has already existed";
        }
        else{
            String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            System.out.println(pw_hash);

            u.setEmail(user.getEmail());
            u.setPassword(pw_hash);
            userRepository.save(u);
        }
            return "added successfully";


    }
}

