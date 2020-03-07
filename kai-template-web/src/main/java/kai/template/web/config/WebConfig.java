package kai.template.web.config;

import kai.template.web.interceptor.AuthInterceptor;
import kai.template.web.interceptor.CrossInterceptor;
import kai.template.web.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc configure
 *
 * @author wangkai
 * @date 2020/2/14 10:46 上午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Bean
//    public DefaultInterceptor defaultInterceptor() {
//        return new DefaultInterceptor();
//    }

    @Bean
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor();
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(defaultInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(crossInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/system/login");
    }
}
