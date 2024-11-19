package com.focus.focus.data.controller;

import com.focus.focus.data.UserRepository;
import com.focus.focus.data.dto.UserDto;
import com.focus.focus.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NoArgsConstructor
@AllArgsConstructor
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user")
    public String list(Model model) {

        model.addAttribute("user", userRepository.findAll());
        return "userList";
    }
    @RequestMapping("/user/{id}")
    public String read(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userRead";
    }

    @RequestMapping("/user/addUser")
    public String addform()  {
        return "addUser";
    }
    @RequestMapping("/user/add")
    public String add(@ModelAttribute UserDto user)  {
        userService.save(user);
        return "redirect:/user";
    }
}
