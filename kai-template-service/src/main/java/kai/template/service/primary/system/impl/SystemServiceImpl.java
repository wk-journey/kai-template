package kai.template.service.primary.system.impl;

import kai.template.datasource.redis.service.RedisService;
import kai.template.exception.ApiError;
import kai.template.service.constant.RedisKeys;
import kai.template.service.exception.BusinessException;
import kai.template.service.primary.system.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 系统Service实现
 *
 * @author wangkai
 * @date 2020/2/14 10:23 下午
 */
@Service
public class SystemServiceImpl implements SystemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Override
    public String login(String userName, String password) {
        String token = this.generateToken(userName);
        return token;
    }

    @Override
    public boolean isLogin(String uid, String token) {
        return false;
    }

    @Override
    public String generateToken(String uid) {
        String token = DigestUtils.md5DigestAsHex((uid + Calendar.getInstance().getTimeInMillis()).getBytes());
        LOGGER.debug("==========generate token {}==========",token);
        String key = String.format(RedisKeys.TOKEN_KEY, uid);
        redisService.set(key, token, RedisKeys.TOKEN_EXPIRE, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public boolean checkUserToken(String uid, String token) throws BusinessException {
        if (StringUtils.isEmpty(uid)) {
            LOGGER.error("uid为空！");
            throw new BusinessException(ApiError.API_PARAMS_NULL);
        }
        if (StringUtils.isEmpty(token)) {
            LOGGER.error("token为空！uid={}", uid);
            throw new BusinessException(ApiError.API_PARAMS_NULL);
        }
        String key = String.format(RedisKeys.TOKEN_KEY, uid);
        String tokenByRedis = redisService.get(key);
        if (StringUtils.isEmpty(tokenByRedis)) {
            LOGGER.error("查询出的token为空！uid={}", uid);
            return false;
        }
        if (!token.equals(tokenByRedis)) {
            LOGGER.error("无效token!uid={}，token={}", uid, token);
            return false;
        }
        return true;
    }

    @Override
    public boolean refreshTokenExpire(String uid) {
        String key = String.format(RedisKeys.TOKEN_KEY, uid);
        Boolean flag = redisService.expire(key, RedisKeys.TOKEN_EXPIRE, TimeUnit.SECONDS);
        return Optional.ofNullable(flag).isPresent() && flag;
    }

    @Override
    public boolean disableToken(String uid) {
        return false;
    }

    @Override
    public String getToken(String uid) {
        return null;
    }
}
