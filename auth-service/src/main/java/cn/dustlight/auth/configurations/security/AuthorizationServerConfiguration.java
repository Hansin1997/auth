package cn.dustlight.auth.configurations.security;

import cn.dustlight.auth.configurations.components.StorageConfiguration;
import cn.dustlight.auth.configurations.documents.DocumentConfiguration;
import cn.dustlight.auth.configurations.components.ServicesConfiguration;
import cn.dustlight.auth.configurations.components.TokenStoreConfiguration;
import cn.dustlight.auth.services.ClientService;
import cn.dustlight.auth.services.UserService;
import cn.dustlight.auth.util.AuthAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableAuthorizationServer
@Import({TokenStoreConfiguration.class,
        ServicesConfiguration.class,
        AuthMethodSecurityConfiguration.class,
        SecurityConfiguration.class,
        DocumentConfiguration.class,
        ResourceServerConfiguration.class,
        HandlerConfiguration.class,
        StorageConfiguration.class})
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserService<?, ?> userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private TokenStore authTokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isFullyAuthenticated()")
                .tokenKeyAccess("isFullyAuthenticated()")
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.userDetailsService(userService)
                .accessTokenConverter(AuthAccessTokenConverter.instance)
                .tokenStore(authTokenStore)
                .authorizationCodeServices(authorizationCodeServices)
                .authenticationManager(authenticationManager);
    }
}
