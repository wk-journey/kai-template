package kai.template.service.utils;

import kai.template.dto.user.UserDto;
import kai.template.persist.entity.user.UserEntity;
import kai.template.utils.safe.Md5Util;
import kai.template.vo.user.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * user Bean 转换器
 *
 * @author wangkai
 * @date 2020/2/19 8:33 下午
 */
public class UserUtils {
    public static UserVo convertUserDto2Vo(UserDto userDto) {
        UserVo userVo = new UserVo();
        if (userDto == null) {
            return userVo;
        }
        BeanUtils.copyProperties(userDto, userVo);
        userVo.setRoles(Stream.of("read", "write").collect(toList()));
        return userVo;
    }

    public static UserEntity convertUserDto2Entity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setPassword(Md5Util.getMd5Salt(userEntity.getPassword(), userEntity.getUserName()));
        return userEntity;
    }
}
