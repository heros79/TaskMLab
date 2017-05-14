package am.david.securityapp.validator;

import am.david.securityapp.model.User;
import am.david.securityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link am.david.securityapp.model.User} Class.
 * Implements {@link Validator} interface
 * Created by David Karchikyan on 5/14/2017.
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public UserValidator() {
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUserName().length() < 6 || user.getUserName().length() > 20) {
            errors.rejectValue("username", "Size.userform.userName");
        }

        if (userService.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("username", "Duplicate.userform.userName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.userform.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userform.password");
        }

    }
}
