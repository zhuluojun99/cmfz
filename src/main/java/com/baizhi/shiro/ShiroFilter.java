package com.baizhi.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFilter {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        HashMap<String, String> map = new HashMap<>();
        map.put("/Admin/login", "anon");
        map.put("/code/getGifCode", "anon");
        map.put("/login.jsp", "anon");
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/jqGrid/**", "anon");
        map.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(map);
        //设置登录页面
        shiroFilter.setLoginUrl("/login.jsp");
        shiroFilter.setSecurityManager(defaultWebSecurityManager);
        return shiroFilter;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm, MemoryConstrainedCacheManager caChe) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(caChe);
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public Realm realm() {
        Realm realm = new Realm();
        return realm;
    }

    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager() {
        MemoryConstrainedCacheManager caChe = new MemoryConstrainedCacheManager();
        return caChe;
    }
}
