package mvc_hiber.controller;

import mvc_hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import mvc_hiber.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }


   /* @RequestMapping(value = "read", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "read";

    } */
   @GetMapping("/")
   public String showUsers(Model model) {
       model.addAttribute("users", userService.getAllUsers());
       return "list-of-users";
   }

    @GetMapping("/showUserForm")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(userService.getUserById(id));
        return "redirect:/";
    }

    @PostMapping("/saveOrUpdateUser")
    public String saveOrUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-form";
        } else {
            userService.save(user);
            return "redirect:/";
        }
    }

}


