package mk.ukim.finki.employees.config;

import mk.ukim.finki.employees.listeners.OAuthSuccessHandler;
import mk.ukim.finki.employees.utility.OAuthUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.filter.CompositeFilter;


import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private final OAuth2ClientContext oauth2ClientContext;
    private final ApplicationEventPublisher applicationEventPublisher;

    public WebSecurityConfiguration(UserDetailsService userDetailsService, @Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext, ApplicationEventPublisher applicationEventPublisher) {
        this.userDetailsService = userDetailsService;
        this.oauth2ClientContext = oauth2ClientContext;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Autowired
    public OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    @ConfigurationProperties("github")
    public OAuthUserResource github() {
        return new OAuthUserResource();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public OAuthUserResource facebook() {
        return new OAuthUserResource();
    }

    @Bean
    @ConfigurationProperties("google")
    public OAuthUserResource google() {
        return new OAuthUserResource();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //todo: roles auth check

        http
                .csrf().disable()
                .addFilterAfter(ssoFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    response.setStatus(HttpStatus.OK.value());
                })
                .failureHandler((request, response, authentication) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                })
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .and()
                .authorizeRequests()
//                .antMatchers("/api/user").hasAnyRole("ROLE_ADMIN")
//                .and()
//                .authorizeRequests()
                .antMatchers("/**", "/", "/login**", "/webjars/**", "/error**", "/api/**").permitAll();

    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filters.add(ssoFilter(google(), "/login/google"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(OAuthUserResource user, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        filter.setAuthenticationSuccessHandler(oAuthSuccessHandler);
        OAuth2RestTemplate template = new OAuth2RestTemplate(user.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                user.getResource().getUserInfoUri(), user.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

}
