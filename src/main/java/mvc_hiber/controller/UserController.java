package mvc_hiber.controller;


import mvc_hiber.model.User;
import mvc_hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
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
        model.addAttribute("users", userService.getAllUsers());
        return "list-of-users";
    }

    @GetMapping("/")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "navigation";
    }


    @PostMapping("showUserForm/add")
    public String addUser(@ModelAttribute User user) {
        if (user.getId() == 0) {
            userService.saveUser(user);
        } else {
            userService.editUser(user);
        }
        return "redirect:/showUserForm";
    }

    @GetMapping("/showUserForm")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getAllUsers());
        return "user-form";
    }

    @GetMapping("showUserForm/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.getAllUsers());


        return "edit-user";
    }

    @PostMapping("showUserForm/edit/showUserForm/edit")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.editUser(user);
        return "redirect:/showUserForm";
    }


    @GetMapping("showUserForm/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/showUserForm";

    }

    @GetMapping("/{id}")
    public String userData(@PathVariable long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "list-of-users";
    }


}