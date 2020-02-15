package kai.template.web.interceptor;

import kai.template.web.constants.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 默认拦截器
 *
 * @author wangkai
 * @date 2020/2/14 11:04 上午
 */
public class DefaultInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultInterceptor.class);

    /**
     * 请求前
     *
     * @param request
     * @param response
     * @param handler
     * @return {@link null}
     * @throws Exception
     * @author Kai
     * @date 2020/2/14 11:05 上午
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("=========================default interceptor pre start=========================");
        LOGGER.info("=========================request info start=========================");
        String uri = request.getRequestURI();
        LOGGER.info("remote is [{}]", request.getRemoteAddr());
        LOGGER.info("request url is [{}]", request.getRequestURL());
        LOGGER.info("queryString is[{}]",request.getQueryString());
        LOGGER.info("====================header start==========================");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            LOGGER.info("[{}]-{}={}", uri, key, value);
        }
        LOGGER.info("====================header end============================");
        LOGGER.info("====================params body start=====================");
        Map<String, String[]> pMap = request.getParameterMap();
        Set<String> pNames = pMap.keySet();
        Iterator<String> keys = pNames.iterator();
        while(keys.hasNext()){
            String key = keys.next();
            Object obj = pMap.get(key);
            if(obj instanceof String[]){
                String[] values = (String[])obj;
                LOGGER.info("[{}]-{}={}", uri, key, Arrays.toString(values));
            }else{
                LOGGER.info("[{}]-{}={}", uri, key, obj);
            }
        }
        LOGGER.info("====================params body end=======================");
        LOGGER.info("[{}]-attr uid={},token={}", uri, request.getAttribute(CommonConstant.UID), request.getAttribute(CommonConstant.TOKEN));
        LOGGER.info("==========================request info end==========================");
        LOGGER.info("==========================default interceptor pre end==========================");
        return true;
    }

    /**
     * 请求后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @return
     * @throws 
     * @author Kai
     * @date 2020/2/14 11:06 上午
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完成后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     * @throws
     * @author Kai
     * @date 2020/2/14 11:07 上午
     */
     @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
