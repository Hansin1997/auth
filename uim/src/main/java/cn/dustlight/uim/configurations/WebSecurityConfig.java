package cn.dustlight.uim.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UimProperties uimProperties;

    @Autowired
    public UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    public SignInSuccessHandler signInSuccessHandler;

    @Autowired
    public SignInFailureHandler signInFailureHandler;

    @Autowired
    public LogoutHandler logoutHandler;

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
    protected void configure(HttpSecurity http) throws Exception {
        String[] publicPaths = uimProperties.getPublicPaths();
        Logger.getLogger(getClass().getName()).info("Public paths: " + Arrays.toString(publicPaths));

        http.authorizeRequests()
                .antMatchers(publicPaths).permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .formLogin()
                .loginPage(uimProperties.getFormLogin().getLoginPage())
                .loginProcessingUrl(uimProperties.getFormLogin().getLoginProcessingUrl())
                .successHandler(signInSuccessHandler)
                .failureHandler(signInFailureHandler)
                .usernameParameter(uimProperties.getFormLogin().getUsernameParameter())
                .passwordParameter(uimProperties.getFormLogin().getPasswordParameter())
                .permitAll()
                .and()
                .logout()
                .logoutUrl(uimProperties.getLogout().getLogoutUrl())
                .deleteCookies(uimProperties.getLogout().getDeleteCookies())
                .logoutSuccessHandler(logoutHandler)
                .permitAll()
                .and()
        ;

        if (uimProperties.isHttpBasicEnabled())
            http.httpBasic();
        else
            http.httpBasic().disable();
        if (uimProperties.isCsrfEnabled())
            http.csrf();
        else
            http.csrf().disable();
    }

}