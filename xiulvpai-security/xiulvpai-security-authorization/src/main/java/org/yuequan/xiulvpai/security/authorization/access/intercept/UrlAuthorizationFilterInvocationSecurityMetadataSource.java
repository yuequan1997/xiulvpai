package org.yuequan.xiulvpai.security.authorization.access.intercept;

import org.yuequan.xiulvpai.security.authorization.permission.AuthorizationDecisionMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * url授权过滤
 * @author yuequan
 * @since 1.0
 **/
@Component
public class UrlAuthorizationFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private AuthorizationDecisionMaker authorizationDecisionMaker;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        var filterInvocation = FilterInvocation.class.cast(o);
        String requestUrl = filterInvocation.getRequestUrl();
        var roles = authorizationDecisionMaker.hasMapping(requestUrl);
        if(roles != null){
            return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
        }

        if(authorizationDecisionMaker.isIgnoreAuthorizationUrl(requestUrl)){
            return null;
        }

        return SecurityConfig.createList("ROLE_UNKNOWN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
