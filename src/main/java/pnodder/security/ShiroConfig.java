package pnodder.security;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:app.properties")
public class ShiroConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(mysqlRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl(env.getProperty("shiro.loginUrl"));
        shiroFilter.setSuccessUrl(env.getProperty("shiro.successfulUrl"));
        shiroFilter.setFilterChainDefinitions(env.getProperty("shiro.filterChainDefinitions"));
        return shiroFilter;
    }

    // must be static, see:
    // https://stackoverflow.com/questions/31388445/apache-shiro-jdbcrealm-with-javaconfig-and-spring-boot
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public JdbcRealm mysqlRealm() {
        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        return realm;
    }

}
