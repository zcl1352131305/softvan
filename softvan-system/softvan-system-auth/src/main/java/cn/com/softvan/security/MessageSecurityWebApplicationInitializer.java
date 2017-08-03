package cn.com.softvan.security;

import cn.com.softvan.config.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * Created by Administrator on 2017/06/08.
 */
@Configuration
public class MessageSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public MessageSecurityWebApplicationInitializer(){
        super(WebSecurityConfig.class);
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }


}