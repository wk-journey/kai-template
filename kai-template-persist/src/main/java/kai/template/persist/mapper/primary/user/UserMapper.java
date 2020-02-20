package kai.template.persist.mapper.primary.user;

import kai.template.persist.entity.user.UserEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<UserEntity> {
    UserEntity selectByUserId(Long userId);

    UserEntity selectByUserName(String userName);
}
