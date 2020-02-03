package kai.template.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Bean(name = "mybatisConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }
}
