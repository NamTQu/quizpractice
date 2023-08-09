/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author Mosena
 */
public class SimpleAuthenticationFailureHandler implements
        AuthenticationFailureHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
            HttpServletResponse response, AuthenticationException exception) 
            throws IOException, ServletException {
        String error;
        if(exception instanceof BadCredentialsException){
            error="InvalidCredentials";
        }
        else if(exception instanceof LockedException){
            error="AccountNotVerified";
        }
        else{
            error="UnknownError";
        }
        request.getSession().setAttribute("error", error);
        redirectStrategy.sendRedirect(request, response, "/login");
    }
    
}
 