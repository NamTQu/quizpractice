/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IAccountRepository;
import swp391.quizpracticing.service.IAccountService;
import swp391.quizpracticing.serviceimple.AccountService;

/**
 *
 * @author Admin
 */
@Controller
public class ChangePassController {
    
    @Autowired
    private IAccountService iAccountService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private IAccountRepository iAccountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/change_password")
    public String showChangePasswordForm(HttpServletRequest request,Model model){
        HttpSession session = request.getSession(false);
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(session != null && session.getAttribute("user") != null){
            model.addAttribute("userSession", session.getAttribute("user"));
            return"common/change_password_form";
        }else{
            return"common/login";
        }
    }
    
    @PostMapping("/change_password")
    public String processChangePassword(HttpServletRequest request,User u,Model model){
            HttpSession session = request.getSession(false);
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            String confirmPass = request.getParameter("confirmPass");
            u = (User) session.getAttribute("user");
            if(oldPass.matches(u.getPassword())){
                if(newPass.matches(confirmPass)){
                    u.setPassword(passwordEncoder.encode(newPass));
                    accountService.updatePassword(u, newPass);
                    return "redirect:/login";
                }else{
                    model.addAttribute("error2", "Incorrect Re-enter New Password");
                    return "common/change_password_form";
                }
            }else{
                model.addAttribute("error1", "Incorrect Old Password");
                return "common/change_password_form";
            }
    }
    
}
