package swp391.quizpracticing.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import swp391.quizpracticing.dto.UserDTO;

@Configuration
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    
    private RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) 
            throws IOException, ServletException {

        HttpSession session;
        UserDTO user=(UserDTO)authentication.getPrincipal();
        session=request.getSession();
        session.setAttribute("user", user);
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest==null){
            redirectStrategy.sendRedirect(request, response, "/");
        }
        else{
            redirectStrategy.sendRedirect(request, response, 
                savedRequest.getRedirectUrl());
        }
    }
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
