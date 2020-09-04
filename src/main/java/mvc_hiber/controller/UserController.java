package mvc_hiber.controller;


import mvc_hiber.model.User;
import mvc_hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UserController {
   // @GetMapping(value = "navigation")
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list-of-users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());//Этот контроллер работает исправно
        return "list-of-users";
    }

    @GetMapping("/")
    public String showAll(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "navigation";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user){
        if(user.getId() == 0){
            userService.saveUser(user);
        }else {
            userService.editUser(user);
        }
        return "redirect/list-of-users";
    }

    @GetMapping("/showUserForm")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }
/*
    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", this.userService.getById(id));
        model.addAttribute("listUser", this.userService.getAll());
        return "list-of-users";
    }

    @GetMapping("/{id}")
    public String userData(@PathVariable long id, Model model) {
        model.addAttribute("user", this.userService.getById(id));
        return "list-of-users";
    }
 */

   @GetMapping("/edit/{id}")
   public String editUser(@PathVariable("id") int id, Model model) {
       User user = userService.getUserById(id);
       model.addAttribute("editUser", user);
       return "user-form";
   }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
       userService.deleteUser(id);
       return "redirect/list-of-users";

    }



}


