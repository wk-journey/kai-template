package kai.template.persist.mapper.primary.user;

import kai.template.persist.entity.user.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserEntity selectByUserName(String userName);
}
