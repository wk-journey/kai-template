package kai.template.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot 多数据源模板
 * @Author: Kai
 * @Date 2020-2-1
 */
@MapperScan("kai.template.persist.mapper")
@ComponentScan(value = {"kai.template"})
@SpringBootApplication
public class KaiTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaiTemplateApplication.class, args);
    }
}
