package kai.template.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Kai
 */
@ComponentScan(value = {"kai.template"})
@MapperScan("kai.template.persist.mapper")
@SpringBootApplication
public class KaiTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaiTemplateApplication.class, args);
    }
}
