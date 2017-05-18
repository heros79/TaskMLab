package am.david.securityapp.controller;

import am.david.securityapp.model.User;
import am.david.securityapp.service.SecurityService;
import am.david.securityapp.service.UserService;
import am.david.securityapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link User} Class.
 *
 * Created by David Karchikyan on 5/14/2017.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    public UserController() {
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (Model model) {

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration (@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLoggin(userForm.getUserName(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loggin (Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "username or password is incorrect");
        }

        if (logout != null) {
            model.addAttribute("massage", "Logged out is succesfully");
        }

        return "login";
    }

    @RequestMapping(value = "/ranklist", method = RequestMethod.GET)
    public String rankList (String logout) {
        if (logout == null) {
            return "ranklist";
        }
        return "/redirect:/login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome (Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin (Model model) {
        return "admin";
    }

}
