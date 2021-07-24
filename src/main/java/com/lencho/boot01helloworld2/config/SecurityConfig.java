package com.lencho.boot01helloworld2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author Lencho
 * @create 2021-07-24 10:46
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        auth.inMemoryAuthentication().withUser("lucy").password(password).roles("admin");
        log.info("123");
    }*/

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    /**
     * 容器中必须注入这个对象，不然密码加密的时候报错
     *
     * @return
     */
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PersistentTokenRepository tokenRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/test/index").permitAll()
                .and().authorizeRequests()
                .antMatchers("/","test/hello","user/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();*/
        http.rememberMe()
                .tokenRepository(tokenRepository)
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60);

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
//                .defaultSuccessUrl("/test/index").permitAll()
                .defaultSuccessUrl("/success.html").permitAll()
                .and().authorizeRequests()
                .antMatchers("/", "test/hello", "user/login").permitAll()
                .antMatchers("/test/index").hasAnyAuthority("admins", "manager")
//                .antMatchers("/test/index").hasAuthority("admins")
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
