package kai.template.web.controller.user;

import kai.template.datasource.redis.service.RedisService;
import kai.template.dto.user.UserDto;
import kai.template.service.primary.user.UserService;
import kai.template.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier(value = "redisService")
    private RedisService redisService;

    @RequestMapping("queryByName")
    public UserDto queryByName(@RequestParam(value = "userName", required = false) String userName) {
        if (StringUtils.isEmpty(userName)) {
            LOGGER.error("参数userName为空！");
            return new UserDto();
        }
        UserDto userDto = userService.selectByUserName(userName);
        return userDto;
    }
}
