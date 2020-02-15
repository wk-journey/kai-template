package kai.template.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求工具类
 *
 * @author wangkai
 * @date 2020/2/14 11:59 上午
 */
public class HttpRequestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String getRequestHeadersFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Access-Control-Request-Headers");
        LOGGER.info("请求通过的http请求头={}", header);
        return header;
    }

    public static String getOriginFromHeader(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        if (StringUtils.isEmpty(origin)) {
            origin = request.getHeader("origin");
        }
        LOGGER.info("获取的跨域域名={}", origin);
        return origin;
    }

    public static Object getParamFromAttribute(HttpServletRequest request, String paramName) {
        Object result = request.getAttribute(paramName);
        if (null == result)
            result = request.getHeader(paramName);
        return result;
    }

    /**
     * 获取请求url
     *
     * @param request
     * @return
     */
    public static String getUrlFromRequest(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getSession().getServletContext().getContextPath() + "/";
    }
}
