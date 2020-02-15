package kai.template.service.primary.user.impl;

import kai.template.dto.user.UserDto;
import kai.template.persist.entity.user.UserEntity;
import kai.template.persist.mapper.primary.user.UserMapper;
import kai.template.service.primary.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto queryByUserName(String userName) {
        try {
            UserEntity userEntity = userMapper.selectByUserName(userName);
            UserDto userDto = new UserDto();
            if (Objects.nonNull(userEntity)) {
                BeanUtils.copyProperties(userEntity, userDto);
            }
            return userDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
