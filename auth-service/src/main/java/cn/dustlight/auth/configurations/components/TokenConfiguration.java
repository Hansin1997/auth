package cn.dustlight.auth.configurations.components;

import cn.dustlight.auth.properties.AuthorizationCodeProperties;
import cn.dustlight.auth.services.EnhancedRedisTokenStore;
import cn.dustlight.auth.services.RedisAuthorizationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class TokenConfiguration {

    @Bean("authTokenStore")
    @ConditionalOnMissingBean(name = "authTokenStore")
    public RedisTokenStore authTokenStore(@Autowired RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean("enhancedTokenStore")
    @ConditionalOnMissingBean(name = "enhancedTokenStore")
    public EnhancedRedisTokenStore enhancedRedisTokenStore(@Autowired RedisConnectionFactory redisConnectionFactory) {
        return new EnhancedRedisTokenStore(redisConnectionFactory);
    }

    @Bean("authApprovalStore")
    @ConditionalOnMissingBean(name = "authApprovalStore")
    public ApprovalStore authApprovalStore(@Autowired TokenStore authTokenStore) {
        TokenApprovalStore approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(authTokenStore);
        return approvalStore;
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
