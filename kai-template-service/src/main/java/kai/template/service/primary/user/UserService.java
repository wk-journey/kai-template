package kai.template.service.primary.user;


import kai.template.dto.user.UserDto;

public interface UserService {
    UserDto selectByUserName(String userName);
}
