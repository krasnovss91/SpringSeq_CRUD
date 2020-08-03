package mvc_hiber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mvc_hiber.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }


    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("allUsers", this.userService.getAllUsers());
        return "read";

    }
}


