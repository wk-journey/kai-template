package kai.template.web.interceptor;

import com.google.common.collect.Lists;
import kai.template.web.utils.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 跨域拦截器
 *
 * @author wangkai
 * @date 2020/2/14 10:49 上午
 */
public class CrossInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(CrossInterceptor.class);

    private final static String CROSS_DOMAIN_ALL_HEADER = "*";
    private final static List<String> CROSS_DOMAIN_ORIGINS = Lists.newArrayList();

    static {
        CROSS_DOMAIN_ORIGINS.add("http://localhost:9527");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("=========================cross interceptor pre start=========================");
        String origin = HttpRequestUtil.getOriginFromHeader(request);
        if (StringUtils.isNotEmpty(origin)) {
            this.doCrossDomain(HttpRequestUtil.getRequestHeadersFromHeader(request), origin, response);
        }
        LOGGER.info("==========================cross interceptor pre end==========================");
        return true;
    }

    private void doCrossDomain(String crossDomainHeader, String origin, HttpServletResponse response) {
        for (String regex : CROSS_DOMAIN_ORIGINS) {
            if (origin.indexOf(regex) >= 0) {
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
                response.setHeader("Access-Control-Allow-Headers", StringUtils.isNotEmpty(crossDomainHeader)
                        ? CROSS_DOMAIN_ALL_HEADER : crossDomainHeader);
                return;
            }
        }
    }
}
