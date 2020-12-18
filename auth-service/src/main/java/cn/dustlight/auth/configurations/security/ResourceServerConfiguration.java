package cn.dustlight.auth.configurations.security;

import cn.dustlight.auth.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.stateless(false);
        resources.accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        Constants.API_ROOT + "/users/**",
                        Constants.API_ROOT + "/authorities",
                        Constants.API_ROOT + "/authorities/**",
                        Constants.API_ROOT + "/roles",
                        Constants.API_ROOT + "/roles/**",
                        Constants.API_ROOT + "/clients",
                        Constants.API_ROOT + "/clients/**",
                        Constants.API_ROOT + "/types",
                        Constants.API_ROOT + "/types/**",
                        Constants.API_ROOT + "/scopes",
                        Constants.API_ROOT + "/scopes/**")
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable()
        ;
    }

}
