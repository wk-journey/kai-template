package kai.template.service.primary.user.impl;

import kai.template.dto.user.UserDto;
import kai.template.persist.entity.user.UserEntity;
import kai.template.persist.mapper.primary.user.UserMapper;
import kai.template.service.primary.user.UserService;
import kai.template.service.utils.UserUtils;
import kai.template.utils.safe.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUserInfo(UserDto userDto) {
        return userMapper.insertSelective(UserUtils.convertUserDto2Entity(userDto));
    }

    @Override
    public int updateUserInfo(UserDto userDto, boolean allFlag) {
        Example example = new Example(UserEntity.class);
        if (allFlag) {
            return userMapper.updateByExample(UserUtils.convertUserDto2Entity(userDto), example);
        }
        return userMapper.updateByExampleSelective(UserUtils.convertUserDto2Entity(userDto), example);
    }

    @Override
    public int saveUserInfo(UserDto userDto) {
        UserDto queryUserDto =  this.queryByUserId(userDto.getUserId());
        if (Objects.nonNull(queryUserDto) && StringUtils.isNotEmpty(queryUserDto.getUserName())) {
            return this.updateUserInfo(userDto, false);
        }
        return this.insertUserInfo(userDto);
    }

    @Override
    public UserDto queryByUserId(Long userId) {
        UserEntity userEntity = userMapper.selectByUserId(userId);
        UserDto userDto = new UserDto();
        if (Objects.nonNull(userEntity)) {
            BeanUtils.copyProperties(userEntity, userDto);
        }
        return userDto;
    }

    @Override
    public UserDto queryByUserName(String userName) {
        UserEntity userEntity = userMapper.selectByUserName(userName);
        UserDto userDto = new UserDto();
        if (Objects.nonNull(userEntity)) {
            BeanUtils.copyProperties(userEntity, userDto);
        }
        return userDto;
    }
}
