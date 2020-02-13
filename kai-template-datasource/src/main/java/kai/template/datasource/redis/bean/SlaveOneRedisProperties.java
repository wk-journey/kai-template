package kai.template.datasource.redis.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangkai on 2018/9/9.
 */
@Component("slaveOneRedisProperties")
@ConfigurationProperties("kai-template.redis.slave-one")
public class SlaveOneRedisProperties extends DefaultRedisProperties {

}
