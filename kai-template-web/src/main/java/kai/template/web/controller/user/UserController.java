package kai.template.web.controller.user;

import kai.template.dto.user.UserDto;
import kai.template.service.primary.user.UserService;
import kai.template.utils.exception.StackTraceLogUtil;
import kai.template.vo.common.response.ErrorResponse;
import kai.template.vo.common.response.SuccessResponse;
import kai.template.web.annotation.NeedNotAuth;
import kai.template.web.controller.BaseController;
import kai.template.service.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("getUserInfo")
    public Object queryUserInfoByUid(HttpServletRequest request) {
        try {
            Long uid = super.getUidFromRequest(request);
            UserDto userDto = userService.queryByUserId(uid);
            return SuccessResponse.newInstance(UserUtils.convertUserDto2Vo(userDto));
        } catch (Exception e) {
            LOGGER.error("根据uid查询用户信息出错！\n{}", StackTraceLogUtil.getStackTraceAsString(e));
            return ErrorResponse.newInstance("查询出错啦！");
        }
    }

    @RequestMapping("queryByUserName")
    public Object queryUserInfoByName(String userName) {
        try {
            if (StringUtils.isEmpty(userName)) {
                LOGGER.error("参数userName为空！");
                return null;
            }
            UserDto userDto = userService.queryByUserName(userName);
            return SuccessResponse.newInstance(UserUtils.convertUserDto2Vo(userDto));
        } catch (Exception e) {
            LOGGER.error("根据用户名查询用户信息出错！\n{}", StackTraceLogUtil.getStackTraceAsString(e));
            return ErrorResponse.newInstance("查询出错啦！");
        }
    }

    @NeedNotAuth
    @RequestMapping("saveUserInfo")
    public Object saveUserInfo(UserDto userDto) {
        try {
            return SuccessResponse.newInstance(userService.saveUserInfo(userDto));
        } catch (Exception e) {
            LOGGER.error("保存用户信息出错！\n{}", StackTraceLogUtil.getStackTraceAsString(e));
            return ErrorResponse.newInstance("保存出错啦！");
        }
    }
}
