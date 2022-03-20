package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.User;
import org.launchcode.hiitworkoutbuilder.models.data.UserRepository;
import org.launchcode.hiitworkoutbuilder.models.dto.UpdateAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/account")
    public String userAccount(Model model, HttpSession session) {
        model.addAttribute(new UpdateAccountDTO());
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        return "/account";
    }

    @PostMapping("/account")
    public String updateAccount(@ModelAttribute @Valid UpdateAccountDTO updateAccountDTO, Errors errors, Model model, HttpSession session, HttpServletRequest request) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());

        if (errors.hasErrors()) {
            return "/account";
        }

        String password = updateAccountDTO.getPassword();

        if (!user.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "/user/account";
        }

        if (user.isMatchingPassword(password) && updateAccountDTO.isDeleteAccount()) {
            userRepository.delete(user);
            request.getSession().invalidate();
            return "redirect:..";
        }

        if (user.isMatchingPassword(password) && !updateAccountDTO.isDeleteAccount()) {
            String newPassword = updateAccountDTO.getNewPassword();
            String verifyPassword = updateAccountDTO.getVerifyPassword();
            if (newPassword.length() < 5 || newPassword.length() > 20) {
                errors.rejectValue("newPassword", "passwords.invalid", "Password must be between 5 and 20 characters");
                return "/account";
            }
            if (!newPassword.equals(verifyPassword)) {
                errors.rejectValue("newPassword", "passwords.mismatch", "Passwords do not match");
                return "/account";
            }
            user.updatePassword(newPassword);
            userRepository.save(user);
            model.addAttribute("passwordUpdated", "Your password has been changed!");
            return "/account";
        }
        return "/account";
    }

}
