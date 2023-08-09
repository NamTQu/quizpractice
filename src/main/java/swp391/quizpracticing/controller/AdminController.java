
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.RoleDTO;
import swp391.quizpracticing.dto.SettingsDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.service.IRoleService;
import swp391.quizpracticing.service.ISettingsService;
import swp391.quizpracticing.service.IUserService;
import swp391.quizpracticing.service.IVerificationService;
import swp391.quizpracticing.service.RegisterService;

/**
 *
 * @author Mosena
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ISettingsService settingsService;
    
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private RegisterService registerService;
    
    @Autowired
    private IVerificationService verifycationService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/user-list")
    public String searchUser(HttpSession session,
            Model model,
            @RequestParam(name = "pageNo", defaultValue = "1") String sPageNo,
            @RequestParam(name="searchValue", required = false) String searchValue,
            @RequestParam(name = "gender", 
                    required = false) String sGender,
            @RequestParam(name = "enable", 
                    required = false) String sStatus,
            @RequestParam(name ="role",
                    required=false) String sRoleId,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "order",defaultValue = "asc") String order){
        int pageNo;
        Integer roleId=null;
        Boolean status=null;
        Boolean gender=null;
        try{
            pageNo=Integer.parseInt(sPageNo);
            if(sRoleId!=null)
                roleId=Integer.parseInt(sRoleId);
            if(sStatus!=null)
                status=Boolean.valueOf(sStatus);
            if(sGender!=null)
                gender=Boolean.valueOf(sGender);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("msg", "Not found");
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            return "/admin/error";
        }
        int pageSize=6;
        Page<UserDTO> users=userService.getUsers(pageNo, pageSize, 
                searchValue, gender, status, roleId, sortBy, order);
        List<RoleDTO> roles=roleService.findRoles();
        int totalPages=users.getTotalPages();
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("roles", roles);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("users", users);
        return "/admin/userlist";
    }
    
    @GetMapping("/settings")
    public String searchSetting(HttpSession session,
            Model model,
            @RequestParam(name = "pageNo", defaultValue = "1") String sPageNo,
            @RequestParam(name="value", required = false) String value,
            @RequestParam(name="type", required = false) String type,
            @RequestParam(name="enable",required = false) String sStatus,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "order",defaultValue = "asc") String order){
        int pageNo;
        Boolean status=null;
        try{
            pageNo=Integer.parseInt(sPageNo);
            if(sStatus!=null)
                status=Boolean.valueOf(sStatus);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("msg", "Not found");
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            return "/admin/error";
        }
        int pageSize=6;
        Page<SettingsDTO>settings=settingsService
                .getSettings(pageNo, pageSize,type, status,
                        sortBy, order, value);
        List<String> types=settingsService.findTypes();
        int totalPages=settings.getTotalPages();
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("types", types);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("settings", settings);
        return "/admin/settings";
    }
    
    @GetMapping("/user-detail")
    public String getUser(HttpSession session, @RequestParam("userId") String sUserId, Model model){
        Integer userId;
        try{
            userId=Integer.parseInt(sUserId);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("msg", "Not found");
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            return "/admin/error";
        }
        UserDTO u=userService.findUser(userId);
        List<RoleDTO> roles=roleService.findRoles();
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("roles", roles);
        model.addAttribute("user", u);
        return "/admin/userdetail";
    }
    @GetMapping("/setting-detail")
    public String getSetting(HttpSession session,@RequestParam("settingId") String sSettingId, Model model){
        Integer settingId;
        try{
            settingId=Integer.parseInt(sSettingId);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("msg", "Not found");
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            return "/admin/error";
        }
        SettingsDTO s=settingsService.findById(settingId);
        List<String> types=settingsService.findTypes();
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("types", types);
        model.addAttribute("setting",s);
        return "/admin/settingdetail";
    }
    @PostMapping("/user-detail/change")
    public String updateUser(HttpSession session,@RequestParam("id") String sId, 
            @RequestParam("role") String sRoleId,
            @RequestParam("enable") String sStatus,
            Model model){
        Integer id,roleId;
        Boolean status;
        try{
            id=Integer.parseInt(sId);
            roleId=Integer.parseInt(sRoleId);
            status=Boolean.valueOf(sStatus);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Error found");
            return "/admin/error";
        }
        model.addAttribute("userSession", session.getAttribute("user"));
        userService.updateUser(id, roleId, status);
        return "redirect:/admin/user-detail?userId="+id;
    }
    
    @PostMapping("/add-user")
    public String addUser(HttpSession session,@RequestParam("fullName") String fullName,
            @RequestParam("role") String sRoleId,
            @RequestParam("email") String email,
            Model model){
        Integer roleId;
        try{
            roleId=Integer.parseInt(sRoleId);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Error found");
            return "/admin/error";
        }
        if(userService.findUserByEmail(email)!=null){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", 
                    "Email has already existed");
            return "/admin/error";
        }
        UserDTO u=new UserDTO();
        String randomCode = RandomString.make(64);
        String randomPass=RandomString.make(12);
        RoleDTO r=roleService.findRole(roleId);
        model.addAttribute("userSession", session.getAttribute("user"));
        u.setToken(randomCode);
        u.setRole(r);
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(randomPass));
        u.setEnable(false);
        u.setToken(randomCode);
        registerService.register(u);
        verifycationService.sendVerification(fullName, email, 
                randomCode,randomPass);
        return "redirect:/admin/user-list";
    }
    @PostMapping("/setting-detail/change")
    public String updateSetting(HttpSession session, Model model,@RequestParam("id") String sId, 
            @RequestParam("value") String value,
            @RequestParam("order") String sOrder,
            @RequestParam("description") String description,
            @RequestParam("status") String sStatus){
        Integer id,order;
        Boolean status;
        try{
            id=Integer.parseInt(sId);
            order=Integer.parseInt(sOrder);
            status=Boolean.valueOf(sStatus);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Error found");
            return "/admin/error";
        }
        model.addAttribute("userSession", session.getAttribute("user"));
        settingsService.updateSettings(id, value, order, status, description);
        return "redirect:/admin/setting-detail?settingId="+id;
    }
    
    @PostMapping("/add-setting")
    public String addSetting(
            HttpSession session,
            Model model,
            @RequestParam("type") String type,
            @RequestParam("value") String value,
            @RequestParam("order") String sOrder,
            @RequestParam("description") String description){
        Integer order;
        try{
            order=Integer.parseInt(sOrder);
        }
        catch(IllegalArgumentException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Error found");
            return "/admin/error";
        }
        model.addAttribute("userSession", session.getAttribute("user"));
        settingsService.addSetting(type, order, value, description);
        return "redirect:/admin/settings";
    }
}

