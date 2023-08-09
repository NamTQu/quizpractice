package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IAccountRepository;
import swp391.quizpracticing.service.IAccountService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
@Controller
public class UserProfileController {
    @Autowired
    IAccountService service;
    
    @Autowired
    ModelMapper map;
    
    @GetMapping("/profile")
    public String show(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("userSession", user);
        if(user.getGender() == true) {
            model.addAttribute("gender", "Male");
        } else {
            model.addAttribute("gender", "Female");
        }
        return "customer/userprofile";
    }
    @GetMapping("/edit_profile")
    public String showeditform(Model model, HttpSession session){
        model.addAttribute("userSession", session.getAttribute("user"));
        return "customer/EditUserprofile";
    }
    @PostMapping("/edit_profile")
    public String edit(HttpServletRequest req, HttpSession session) {
        UserDTO userSession = (UserDTO) session.getAttribute("user");
        userSession.setFullName(req.getParameter("fullName"));
        userSession.setAddress(req.getParameter("address"));
        boolean gender = false;
        if(req.getParameter("gender").equals("true")) {
            gender = true;
        }
        userSession.setGender(gender);
        userSession.setMobile(req.getParameter("mobile"));
        service.updateUserProfile(userSession);
        session.setAttribute("user", userSession);
        
        return "redirect:/profile";
    }
}
