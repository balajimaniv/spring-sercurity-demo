package com.cg.springsercuritydemo.controllers;


import com.cg.springsercuritydemo.models.User;
import com.cg.springsercuritydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.constraints.Email;
import java.awt.*;

//@RestController
@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String name, @RequestParam String emailID
            , @RequestParam String contactNumber, @RequestParam String address) {
        User user = new User();
        user.setName(name);
        user.setEmailID(emailID);
        user.setContactNumber(contactNumber);
        user.setAddress(address);

        userRepository.save(user);

        return "redirect:/show/" + user.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

        return "redirect:/user";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String name, @RequestParam String emailID
            , @RequestParam String contactNumber, @RequestParam String address) {
        User user = userRepository.findById(id).get();
        user.setName(name);
        user.setEmailID(emailID);
        user.setContactNumber(contactNumber);
        user.setAddress(address);
        userRepository.save(user);

        return "redirect:/show/" + user.getId();
    }
//
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void create(@RequestBody User user) {
//
//        userRepository.save(user);
//
//    }
//    @RequestMapping(value = "/{id}")
//    public User read(@PathVariable String id) {
//        System.out.println(" inside get");
//        return userRepository.findById(id).get();
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@RequestBody User user) {
//        userRepository.save(user);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable String id) {
//        userRepository.delete(userRepository.findById(id).get());
//    }

}
