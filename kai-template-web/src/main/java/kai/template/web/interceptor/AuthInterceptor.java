package kai.template.web.interceptor;

import kai.template.exception.ApiError;
import kai.template.service.primary.system.SystemService;
import kai.template.web.annotation.NeedNotAuth;
import kai.template.web.constants.CommonConstant;
import kai.template.web.utils.HttpRequestUtil;
import kai.template.web.utils.HttpResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 权限拦截器
 *
 * @author wangkai
 * @date 2020/2/14 10:48 上午
 */
public class AuthInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private SystemService systemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("=========================auth interceptor pre start=========================");
        // @NeedNotAuth注解的Controller可无需登录直接访问
        if (handler instanceof HandlerMethod && (null != ((HandlerMethod) handler).getMethodAnnotation(NeedNotAuth.class))) {
            LOGGER.info("==========================auth interceptor pre end==========================");
            return true;
        }

        // option请求直接通过
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            LOGGER.info("==========================auth interceptor pre end==========================");
            return true;
        }

        Object uid = HttpRequestUtil.getParamFromAttribute(request, CommonConstant.UID);
        Object token = HttpRequestUtil.getParamFromAttribute(request, CommonConstant.TOKEN);
        // uid和token空值校验
        if (!Optional.ofNullable(uid).isPresent() || !Optional.ofNullable(token).isPresent()) {
            HttpResponseUtil.parseErrorResponse(ApiError.SYS_UNSIGNED, response);
            LOGGER.info("==========================auth interceptor pre end==========================");
            return false;
        }
        // 是否已经登录
        Boolean login = systemService.isLogin(Long.parseLong(uid.toString()), String.valueOf(token));
        if (!Optional.ofNullable(login).isPresent()) {
            HttpResponseUtil.parseErrorResponse(ApiError.SYS_UNSIGNED, response);
            LOGGER.info("==========================auth interceptor pre end==========================");
            return false;
        }
        // 单点登录校验
        if (!login) {
            HttpResponseUtil.parseErrorResponse(ApiError.SYS_REPEAT_LOGIN, response);
            LOGGER.info("==========================auth interceptor pre end==========================");
            return false;
        }
        // 登录成功
        LOGGER.info("==========================auth interceptor pre end==========================");
        return true;
    }
}
