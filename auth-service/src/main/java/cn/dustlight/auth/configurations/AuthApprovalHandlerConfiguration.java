package cn.dustlight.auth.configurations;

import cn.dustlight.auth.services.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class AuthApprovalHandlerConfiguration {

    @Bean("authApprovalHandler")
    @ConditionalOnMissingBean(name = "authApprovalHandler")
    public AuthApprovalHandler authApprovalHandler(@Autowired ScopeService scopeService) {
        return new AuthApprovalHandler(scopeService);
    }

    public static class AuthApprovalHandler extends ApprovalStoreUserApprovalHandler {

        private final ScopeService scopeService;

        public AuthApprovalHandler(ScopeService scopeService) {
            this.scopeService = scopeService;
        }

        @Override
        public void afterPropertiesSet() {

        }

        @Override
        public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
            AuthorizationRequest result = super.checkForPreApproval(authorizationRequest, userAuthentication);
            updateAuthorities(result);
            return result;
        }

        @Override
        public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
            AuthorizationRequest result = super.updateAfterApproval(authorizationRequest, userAuthentication);
            updateAuthorities(result);
            return result;
        }

        /**
         * 从Scope获取Authorities
         */
        private void updateAuthorities(AuthorizationRequest authorizationRequest) {
            Set<String> scope;
            if (authorizationRequest.isApproved() &&
                    (scope = authorizationRequest.getScope()) != null &&
                    !scope.isEmpty()) {
                Collection<String> authorities = scopeService.listScopeAuthorities(scope);
                Set<GrantedAuthority> au = new LinkedHashSet<>();
                au.addAll(authorizationRequest.getAuthorities());
                au.addAll(AuthorityUtils.createAuthorityList(authorities.toArray(new String[0])));
                authorizationRequest.setAuthorities(au);
            }
        }
    }
}
