package kai.template.vo.common.response;

import kai.template.vo.common.base.BaseBean;

/**
 *
 */
public class Response extends BaseBean {
    // 成功或失败
    private boolean success = true;
    // 状态码
    private int code;
    // 信息
    private String message;
    // 扩展码
    private Integer extCode;
    // 扩展信息
    private String extInfo;

    public Response() {
    }

    public Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getExtCode() {
        return extCode;
    }

    public void setExtCode(Integer extCode) {
        this.extCode = extCode;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}
