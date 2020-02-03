package kai.template.datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "kai.template.persist.mapper.secondary",
        sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfig {
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(
            @Qualifier("secondaryDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis配置xml文件地址
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/secondary/**/*.xml"));
        return bean.getObject();
    }

    @Bean("secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("secondarySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
