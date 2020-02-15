package kai.template.service.primary.system;

import kai.template.service.exception.BusinessException;

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
     String login(String userName, String password);

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
     boolean isLogin(String uid, String token);

     String generateToken(String uid);

     boolean checkUserToken(String uid, String token) throws BusinessException;

     boolean refreshTokenExpire(String uid);

     boolean disableToken(String uid);

     String getToken(String uid);
}
