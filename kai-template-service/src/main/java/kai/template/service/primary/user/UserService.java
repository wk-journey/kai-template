package kai.template.service.primary.user;

import kai.template.dto.user.UserDto;

public interface UserService {

    /**
     * 插入新用户信息
     *
     * @param userDto
     * @return {@link int}
     * @throws
     * @author Kai
     * @date 2020/2/20 2:49 下午
     */
    int insertUserInfo(UserDto userDto);

    /**
     * 更新新用户信息
     *
     * @param userDto
     * @param allFlag  是否完全覆盖
     * @return {@link int}
     * @throws
     * @author Kai
     * @date 2020/2/20 2:49 下午
     */
    int updateUserInfo(UserDto userDto, boolean allFlag);

    /**
     * 保存用户信息
     *
     * @param userDto
     * @return {@link int}
     * @throws
     * @author Kai
     * @date 2020/2/20 2:49 下午
     */
     int saveUserInfo(UserDto userDto);

    /**
     * 根据名字获取用户基本信息
     *
     * @param userId
     * @return {@link UserDto}
     * @throws
     * @author Kai
     * @date 2020/2/3 6:57 下午
     */
    UserDto queryByUserId(Long userId);

    /**
     * 根据名字获取用户基本信息
     *
     * @param userName
     * @return {@link UserDto}
     * @throws
     * @author Kai
     * @date 2020/2/3 6:57 下午
     */
     UserDto queryByUserName(String userName);
}
