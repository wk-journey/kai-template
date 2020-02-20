package kai.template.vo.system;

/**
 * TODO
 *
 * @author wangkai
 * @date 2020/2/15 7:32 下午
 */
public class SystemLoginResultVo {
    private Long uid;
    private String token;

    public SystemLoginResultVo() {
    }

    public SystemLoginResultVo(Long uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    public static SystemLoginResultVo newInstance(Long uid, String token) {
        return new SystemLoginResultVo(uid, token);
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
