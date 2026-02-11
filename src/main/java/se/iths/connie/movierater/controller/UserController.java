package se.iths.connie.movierater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.connie.movierater.model.User;
import se.iths.connie.movierater.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        return "add-user";
    }

    @PostMapping
    public String addUser(@ModelAttribute User user) {
        User user2 = userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        User user2 = userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "update-user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }


}
