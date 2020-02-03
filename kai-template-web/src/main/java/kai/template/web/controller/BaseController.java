package kai.template.web.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {
    /**
     * 获取请求url
     *
     * @param request
     * @return
     */
    protected String getUrlFromRequset(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getSession().getServletContext().getContextPath() + "/";
    }
}
