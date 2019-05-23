package mk.ukim.finki.employees.listeners;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2Authentication a = (OAuth2Authentication) authentication;
        Map<String, Object> map = (Map<String, Object>) a.getUserAuthentication().getDetails();

        String username = (map.get("name") != null ? map.get("name").toString() : map.get("login").toString());
        String id = (map.get("id") != null ? map.get("id").toString() : map.get("sub").toString());

        // todo: change this to go to some client page -- this is once you sing in with fb git google
        response.sendRedirect("http://localhost:8080/api/clients/catalogue/all");
    }


}
