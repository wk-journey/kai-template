package kai.template.service.constant;

/**
 * TODO
 *
 * @author wangkai
 * @date 2020/2/14 10:56 下午
 */
public class RedisKeys {
    /** token超时时间1h，单位秒 */
    public final static int TOKEN_EXPIRE = 3600;
    /** 登录token */
    public final static String TOKEN_KEY = "kai-template:token:uid:%s";
}
