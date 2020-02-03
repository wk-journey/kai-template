package kai.template.service.primary.user;


import kai.template.dto.user.UserDto;

public interface UserService {

    /**
     * 根据名字获取用户基本信息
     *
     * @param userName
     * @return {@link UserDto}
     * @throws
     * @author Kai
     * @date 2020/2/3 6:57 下午
     */
     UserDto selectByUserName(String userName);
}
