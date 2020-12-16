package cn.dustlight.auth.configurations;

import cn.dustlight.auth.generator.Generator;
import cn.dustlight.auth.generator.UniqueGenerator;
import cn.dustlight.auth.mappers.*;
import cn.dustlight.auth.properties.AuthorizationCodeProperties;
import cn.dustlight.auth.properties.PatternProperties;
import cn.dustlight.auth.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import java.util.regex.Pattern;

@EnableConfigurationProperties({PatternProperties.class, AuthorizationCodeProperties.class})
public class ServicesConfiguration {

    @Bean("userService")
    @ConditionalOnMissingBean(name = "userService")
    public UserService<?, ?> userService(@Autowired UserMapper userMapper,
                                         @Autowired RoleMapper roleMapper,
                                         @Autowired PasswordEncoder passwordEncoder,
                                         @Autowired UniqueGenerator<Long> idGenerator,
                                         @Autowired PatternProperties properties) {
        DefaultUserService userService = new DefaultUserService(userMapper, roleMapper, passwordEncoder, idGenerator);
        if (properties != null) {
            if (properties.getUsername() != null)
                userService.setUsernamePattern(Pattern.compile(properties.getUsername()));
            if (properties.getPassword() != null)
                userService.setPasswordPattern(Pattern.compile(properties.getPassword()));
            if (properties.getEmail() != null)
                userService.setEmailPattern(Pattern.compile(properties.getEmail()));
        }
        return userService;
    }

    @Bean("clientService")
    @ConditionalOnMissingBean(name = "clientService")
    public ClientService clientService(@Autowired ClientMapper mapper,
                                       @Autowired ScopeMapper scopeMapper,
                                       @Autowired GrantTypeMapper grantTypeMapper,
                                       @Autowired UniqueGenerator<String> clientIdGenerator,
                                       @Autowired Generator<String> clientSecretGenerator,
                                       @Autowired PasswordEncoder passwordEncoder) {
        return new DefaultClientService(mapper, scopeMapper, grantTypeMapper, clientIdGenerator,
                clientSecretGenerator, passwordEncoder);
    }

    @Bean("authorityService")
    @ConditionalOnMissingBean(name = "authorityService")
    public AuthorityService authorityService(@Autowired AuthorityMapper mapper,
                                             @Autowired UniqueGenerator<Long> generator) {
        return new DefaultAuthorityService(mapper, generator);
    }

    @Bean("roleService")
    @ConditionalOnMissingBean(name = "roleService")
    public RoleService roleService(@Autowired RoleMapper roleMapper,
                                   @Autowired AuthorityMapper authorityMapper,
                                   @Autowired UniqueGenerator<Long> generator) {
        return new DefaultRoleService(generator, roleMapper, authorityMapper);
    }

    @Bean("scopeService")
    @ConditionalOnMissingBean(name = "scopeService")
    public ScopeService scopeService(@Autowired ScopeMapper scopeMapper,
                                     @Autowired AuthorityMapper authorityMapper,
                                     @Autowired UniqueGenerator<Long> generator) {
        return new DefaultScopeService(generator, scopeMapper, authorityMapper);
    }

    @Bean("grantTypeService")
    @ConditionalOnMissingBean(name = "grantTypeService")
    public GrantTypeService grantTypeService(@Autowired GrantTypeMapper grantTypeMapper,
                                             @Autowired UniqueGenerator<Long> generator) {
        return new DefaultGrantTypeService(grantTypeMapper, generator);
    }

    @Bean("authorizationCodeServices")
    @ConditionalOnMissingBean(name = "authorizationCodeServices")
    public AuthorizationCodeServices authorizationCodeServices(@Autowired RedisConnectionFactory factory,
                                                               @Autowired AuthorizationCodeProperties properties) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.java());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.java());
        RedisAuthorizationCodeService instance = new RedisAuthorizationCodeService(redisTemplate);
        if (properties != null) {
            if (properties.getDuration() != null)
                instance.setDuration(properties.getDuration());
            if (properties.getKeyPrefix() != null)
                instance.setKeyPrefix(properties.getKeyPrefix());
        }
        return instance;
    }
}
