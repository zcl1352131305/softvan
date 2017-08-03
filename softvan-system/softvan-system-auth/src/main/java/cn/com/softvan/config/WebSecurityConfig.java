package cn.com.softvan.config;

import cn.com.softvan.security.RestAccessDeniedHandler;
import cn.com.softvan.security.custom.CustomAccessDecisionManager;
import cn.com.softvan.security.custom.CustomAuthenticationUserDetailsService;
import cn.com.softvan.security.custom.CustomPreAuthenticationFilter;
import cn.com.softvan.security.custom.CustomSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码验证
        //auth.authenticationProvider(authenticationProvider());
        //token验证
        auth.authenticationProvider(preAuthenticationProvider());
    }

    /**
     * 基于token验证
     * @return
     */
    private AuthenticationProvider preAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new CustomAuthenticationUserDetailsService());
        return provider;
    }

    /**
     * 自定义token认证
     * @return
     * @throws Exception
     */
    @Bean
    public CustomPreAuthenticationFilter headerAuthenticationFilter() throws Exception {
        return new CustomPreAuthenticationFilter(authenticationManager(), new SimpleUrlAuthenticationFailureHandler());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.addFilter(headerAuthenticationFilter());
        http
                .csrf().disable()
                //采用无状态机制（不使用session）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               /* .and()
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()*/
                .and().addFilter(headerAuthenticationFilter())


               /* .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginCheck")*/
                //登陆成功后的处理，因为是API的形式所以不用跳转页面
                //.successHandler(new RestAuthenticationSuccessHandler())
                //登陆失败后的处理
                //.failureHandler(new SimpleUrlAuthenticationFailureHandler())
                //.permitAll()
//                .and()
//                .logout()
//                .permitAll()

                .exceptionHandling()

                //.authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .and().authorizeRequests().anyRequest().authenticated()
                //设置自定义拦截器
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        fsi.setSecurityMetadataSource(securityMetadataSource());
                        return fsi;
                    }
                });

    }


    /**
     * 决策器
     * @return
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    /**
     * 自定义权限读取
     * @return
     */
    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource();
    }


}
