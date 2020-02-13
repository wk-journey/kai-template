package kai.template.datasource.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by wangkai on 2018/9/9.
 */
public class RedisServiceImpl extends AbstractRedisService {
    private RedisTemplate redisTemplate;

    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}
