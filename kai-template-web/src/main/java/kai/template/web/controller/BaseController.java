package kai.template.web.controller;

import kai.template.web.constants.CommonConstant;
import kai.template.web.controller.system.login.LoginController;
import kai.template.web.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected Long getUidFromRequest(HttpServletRequest request) {
        Object uid = HttpRequestUtil.getParamFromAttribute(request, CommonConstant.UID);
        return Long.valueOf(uid.toString());
    }
}
