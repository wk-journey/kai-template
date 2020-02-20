package kai.template.web.controller.system.login;

import kai.template.service.exception.BusinessException;
import kai.template.service.primary.system.SystemService;
import kai.template.utils.exception.StackTraceLogUtil;
import kai.template.vo.common.response.ErrorResponse;
import kai.template.vo.common.response.SuccessResponse;
import kai.template.vo.system.SystemLoginFormVo;
import kai.template.vo.system.SystemLoginResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录Controller
 *
 * @author wangkai
 * @date 2020/2/14 1:05 下午
 */
@RestController
@RequestMapping("/system/")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SystemService systemService;

    @PostMapping("login")
    public Object login(@RequestBody SystemLoginFormVo form) {
        try {
            SystemLoginResultVo systemLoginResultVo = systemService.login(form.getUserName(), form.getPassword());
            return SuccessResponse.newInstance(systemLoginResultVo);
        } catch (BusinessException be) {
            LOGGER.error(StackTraceLogUtil.getStackTraceAsString(be));
            return ErrorResponse.newInstance("获取token失败！");
        } catch (Exception e) {
            LOGGER.error(StackTraceLogUtil.getStackTraceAsString(e));
            return ErrorResponse.newInstance("获取token失败！");
        }
    }
}
