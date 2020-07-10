package com.scb.test.common.config;

import com.scb.test.common.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description 权限配置
 * @Author zhangheng
 */
@EnableWebSecurity
public class MySecutityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/index").failureUrl("/login.html");

        http.authorizeRequests().antMatchers("/login","/login.html").permitAll()
               .antMatchers("/user/**").hasRole(Contants.SYS)
               .antMatchers("/student/**").hasRole(Contants.STUDENT)
               .antMatchers("/teacher/**").hasRole(Contants.TEACHER)
               .antMatchers("/h2/**").permitAll() // 放行 H2 的请求
               .anyRequest().authenticated()
               .and().csrf().ignoringAntMatchers("/h2/**") // 禁用 H2 控制台的 CSRF 防护
               .and().headers().frameOptions().sameOrigin()// 允许来自同一来源的 H2 控制台的请求
               .and().csrf().disable();

       http.logout().logoutSuccessUrl("/login.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        MySecutityConfig config = new MySecutityConfig();
//        System.out.println(config.passwordEncoder().encode("123"));
//    }
}
