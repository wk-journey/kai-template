package kai.template.service.primary.system;

import kai.template.service.exception.BusinessException;
import kai.template.vo.system.SystemLoginResultVo;

/**
 * 系统Service
 *
 * @author wangkai
 * @date 2020/2/14 10:22 下午
 */
public interface SystemService {

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return {@link String}
     * @throws
     * @author Kai
     * @date 2020/2/14 10:31 下午
     */
    SystemLoginResultVo login(String userName, String password) throws BusinessException;

    /**
     * 用户是否正常登录
     *
     * @param uid
     * @param token
     * @return {@link boolean}
     * @throws
     * @author Kai
     * @date 2020/2/14 10:29 下午
     */
     Boolean isLogin(Long uid, String token);

     String generateToken(Long uid);

     boolean checkUserToken(Long uid, String token) throws BusinessException;

     boolean refreshTokenExpire(Long uid) throws BusinessException;

     boolean disableToken(Long uid);

     String getToken(Long uid);
}
