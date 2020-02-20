package kai.template.service.primary.system.impl;

import kai.template.datasource.redis.service.RedisService;
import kai.template.dto.user.UserDto;
import kai.template.exception.ApiError;
import kai.template.service.constant.RedisKeys;
import kai.template.service.exception.BusinessException;
import kai.template.service.primary.system.SystemService;
import kai.template.service.primary.user.UserService;
import kai.template.utils.exception.StackTraceLogUtil;
import kai.template.utils.safe.Md5Util;
import kai.template.vo.system.SystemLoginResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Calendar;
import java.util.Objects;
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
    @Autowired
    private UserService userService;

    @Override
    public SystemLoginResultVo login(String userName, String password) throws BusinessException {
        // 参数校验
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            LOGGER.error("用户名或密码为空！userName={}，password={}", userName, password);
            throw new BusinessException(ApiError.API_PARAMS_NULL);
        }
        // 查询用户名是否在数据库中真实存在
        UserDto userDto = userService.queryByUserName(userName);
        if (Objects.isNull(userDto) || Objects.isNull(userDto.getUserId())) {
            LOGGER.error("校验密码时查询不到用户记录！userName={}", userName);
            throw new BusinessException(ApiError.MYSQL_QUERY_NULL);
        }
        // 校验密码
        String saltPassword = Md5Util.getMd5Salt(password, userName);
        if (!saltPassword.equals(userDto.getPassword())) {
            LOGGER.error("密码错误！userName={}，password={}", userName, password);
            throw new BusinessException(ApiError.SYS_USER_ERROR);
        }
        // 根据获取到的userId设置加密token
        Long userId = userDto.getUserId();
        String token = this.generateToken(userId);
        // 返回uid和token作为单点登录的cookies依据
        return SystemLoginResultVo.newInstance(userId, token);
    }

    @Override
    public Boolean isLogin(Long uid, String token) {
        try {
            return this.checkUserToken(uid, token) && this.refreshTokenExpire(uid);
        } catch (BusinessException e) {
            LOGGER.error(StackTraceLogUtil.getStackTraceAsString(e));
            if (ApiError.API_RESULT_NOT_SAME.getCode() == e.getErrorCode()) {
                return false;
            }
            return null;
        }
    }

    @Override
    public String generateToken(Long uid) {
        String token = DigestUtils.md5DigestAsHex((String.valueOf(uid)
                + Calendar.getInstance().getTimeInMillis()).getBytes());
        LOGGER.debug("==========generate token {}==========",token);
        String key = String.format(RedisKeys.TOKEN_KEY, uid);
        redisService.set(key, token, RedisKeys.TOKEN_EXPIRE, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public boolean checkUserToken(Long uid, String token) throws BusinessException {
        if (!Optional.ofNullable(uid).isPresent()) {
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
            new BusinessException(ApiError.REDIS_QUERY_NULL);
        }
        if (!token.equals(tokenByRedis)) {
            LOGGER.error("无效token!uid={}，token={}", uid, token);
            new BusinessException(ApiError.API_RESULT_NOT_SAME);
        }
        return true;
    }

    @Override
    public boolean refreshTokenExpire(Long uid) throws BusinessException {
        String key = String.format(RedisKeys.TOKEN_KEY, uid);
        Boolean flag = redisService.expire(key, RedisKeys.TOKEN_EXPIRE, TimeUnit.SECONDS);
        if (Optional.ofNullable(flag).isPresent() && flag) {
            return true;
        }
        throw new BusinessException(ApiError.REDIS_EXPIRE_FAIL);
    }

    @Override
    public boolean disableToken(Long uid) {
        return false;
    }

    @Override
    public String getToken(Long uid) {
        return null;
    }
}
