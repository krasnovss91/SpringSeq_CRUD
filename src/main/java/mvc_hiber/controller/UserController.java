package mvc_hiber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import mvc_hiber.model.User;
import mvc_hiber.service.SecurityService;
import mvc_hiber.service.UserService;
import mvc_hiber.validator.UserValidator;

@Controller
public class UserController {
    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService=securityService;
        this.userValidator=userValidator;
    }


    @Deprecated
//Добавить маппинг на страницу login
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("login") User user, Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String readUserList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "navigation";
    }
 */

    @RequestMapping(value = "/admin/read", method = RequestMethod.GET)
    public String readUserList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "read";
    }


 /*
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String addPage(@ModelAttribute("registration") User user, Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("registration") User user) {

        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.saveUser(userForm);

        securityService.autoLogin(userForm.getName(), userForm.getConfirmPassword());

        return "redirect:/navigation";
    }

*/

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.editUser(user);
        model.addAttribute("allUsers", userService.getAllUsers());
        // return "redirect:/read";
        return "read";
    }


    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "delete";
    }


    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") User user, Model model) {
        userService.deleteUser(user.getId());
        model.addAttribute("allUsers", userService.getAllUsers());
        //  return "redirect:/read";
        return "read";
    }


    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "read";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        
        return "login";
    }

    /*
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(User user) {

        if (user.getRole().equals("admin")) {
            return "redirect/admin";
        }

        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {

        return "admin";
    }


 */

}
