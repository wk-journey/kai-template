package kai.template.datasource.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import kai.template.datasource.redis.bean.DefaultRedisProperties;
import kai.template.datasource.redis.bean.IRedisProperties;
import kai.template.datasource.redis.bean.SlaveOneRedisProperties;
import kai.template.datasource.redis.service.RedisService;
import kai.template.datasource.redis.service.RedisServiceImpl;
import kai.template.datasource.redis.service.SlaveOneRedisServiceImpl;
import kai.template.datasource.utils.SeparatorParserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by wangkai on 2018/9/8.
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfiguration {

//    @Bean("defaultRedisProperties")
//    public DefaultRedisProperties defaultRedisProperties() {
//        return new DefaultRedisProperties();
//    }
//
//    @Bean("slaveOneRedisProperties")
//    public SlaveOneRedisProperties slaveOneRedisProperties() {
//        return new SlaveOneRedisProperties();
//    }

    @Primary
    @Bean(name = "redisService")
    @ConditionalOnMissingBean(name = {"redisService"})
    public RedisService redisService(@Qualifier("defaultRedisProperties") DefaultRedisProperties defaultRedisProperties) {
        return new RedisServiceImpl(this.defaultRedisTemplateNoTransaction(defaultRedisProperties));
    }

    @Bean(name = "slaveOneRedisService")
    @ConditionalOnMissingBean(name = {"slaveOneRedisService", "redisService"})
    public RedisService anotherRedisService(@Qualifier("slaveOneRedisProperties") SlaveOneRedisProperties slaveOneRedisProperties) {
        return new SlaveOneRedisServiceImpl(this.slaveOneRedisTemplateNoTransaction(slaveOneRedisProperties));
    }

    @Bean(name = "redisTemplate")
    public StringRedisTemplate defaultRedisTemplateNoTransaction(DefaultRedisProperties defaultRedisProperties) {
        StringRedisTemplate template = new StringRedisTemplate(
                this.persistRedisConnectFactory(defaultRedisProperties)
        );
        this.setRedisTemplateProperties(template);
        return template;
    }

    @Bean(name = "slaveOneRedisTemplate")
    @ConditionalOnMissingBean(name = {"redisTemplate"})
    public StringRedisTemplate slaveOneRedisTemplateNoTransaction(SlaveOneRedisProperties slaveOneRedisProperties) {
        StringRedisTemplate template = new StringRedisTemplate(
                this.persistRedisConnectFactory(slaveOneRedisProperties)
        );
        this.setRedisTemplateProperties(template);
        return template;
    }


    private JedisConnectionFactory persistRedisConnectFactory(IRedisProperties redisProperties) {
        JedisConnectionFactory factory;
        /**
         * 模式选择顺序：集群 > 哨兵 > 单机
         * 不支持简单的 master/slave模式
         */
        // 集群模式
        if (StringUtils.isNotBlank(redisProperties.getClusterNodes())) {
            factory = this.createClusterFactory(redisProperties);
        } else if (StringUtils.isNotBlank(redisProperties.getSentinelMaster())
                && StringUtils.isNotBlank(redisProperties.getSentinelNodes())) {
            factory = this.createSentinelFactory(redisProperties);
        } else if (StringUtils.isNotBlank(redisProperties.getHost())
                && StringUtils.isNotBlank(redisProperties.getPort())){
            factory = this.createSingleFactory(redisProperties);
        } else {
            throw new RuntimeException("redis configuration error!");
        }

        // Jedis池配置
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大空闲连接数量
        config.setMaxIdle(redisProperties.getPoolMaxIdle());
        // 最小空闲连接数
        config.setMinIdle(redisProperties.getPoolMinIdle());
        // 池中持有的最大连接数
        config.setMaxTotal(redisProperties.getPoolMaxTotal());

        // borrowObject 方法的最大等待时间
        config.setMaxWaitMillis(redisProperties.getPoolMaxWaitMillis());
        // 池中可用资源耗尽时，borrow 方法是否阻塞等待 maxWaitMillis 毫秒
        config.setBlockWhenExhausted(true);
        // borrowObject 时是否执行检测
        config.setTestOnBorrow(true);

        // 是否检测空闲连接链接的有效性，间隔时间为 timeBetweenEvictionRunsMillis
        config.setTestWhileIdle(true);
        config.setTestOnCreate(false);
        config.setTestOnReturn(false);

        // 空闲对象被清除需要达到的最小空闲时间
        config.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);
        // 空闲检测线程，sleep 间隔多长时间,去处理与 idle 相关的事情
        config.setTimeBetweenEvictionRunsMillis(-1L);

        factory.setPoolConfig(config);
        factory.setDatabase(redisProperties.getDatabase());
        factory.setTimeout(redisProperties.getTimeout());
        factory.setUsePool(true);

        // 有密码时才设置密码
        if(StringUtils.isNotBlank(redisProperties.getPassword())){
            factory.setPassword(redisProperties.getPassword());
        }

        // 初始化
        factory.afterPropertiesSet();
        return factory;
    }

    private JedisConnectionFactory createClusterFactory(IRedisProperties redisProperties) {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        String nodes = redisProperties.getClusterNodes();
        List<SeparatorParserUtil.SplitItem> items = SeparatorParserUtil.split2List(nodes, ",", ":");
        for (SeparatorParserUtil.SplitItem item : items) {
            configuration.addClusterNode(new RedisNode(item.getFront(), Integer.valueOf(item.getBehind())));
        }
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        return factory;
    }

    private JedisConnectionFactory createSentinelFactory(IRedisProperties redisProperties) {
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
        configuration.setMaster(redisProperties.getSentinelMaster());
        String nodes = redisProperties.getSentinelNodes();
        List<SeparatorParserUtil.SplitItem> items = SeparatorParserUtil.split2List(nodes, ",", ":");
        for (SeparatorParserUtil.SplitItem item : items) {
            configuration.addSentinel(new RedisNode(item.getFront(), Integer.valueOf(item.getBehind())));
        }
        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        return factory;
    }

    private JedisConnectionFactory createSingleFactory(IRedisProperties redisProperties) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisProperties.getHost());
        factory.setPort(Integer.valueOf(redisProperties.getPort()));
        return factory;
    }

    private void setRedisTemplateProperties(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setEnableDefaultSerializer(false);
        template.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));

        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
    }
}
