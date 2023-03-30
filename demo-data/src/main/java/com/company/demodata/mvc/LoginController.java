package com.company.demodata.mvc;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/oauth")
    public String oauthLogin(Model model) {
        DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String token = user.getIdToken().getTokenValue();
        String username = user.getPreferredUsername();

        model.addAttribute("token", token);
        model.addAttribute("username", username);
        return "oauth";
    }
}
