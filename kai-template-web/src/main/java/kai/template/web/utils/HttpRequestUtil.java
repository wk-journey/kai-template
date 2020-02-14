package kai.template.web.utils;

import kai.template.web.interceptor.CrossInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author wangkai
 * @date 2020/2/14 11:59 上午
 */
public class HttpRequestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String getRequestHeadersFromHeader(HttpServletRequest request){
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

    public static Object getParamFromAttribute(HttpServletRequest request, String paramName){
        Object result = request.getAttribute(paramName);
        if(null == result)
            result = request.getHeader(paramName);
        return result;
    }
}
