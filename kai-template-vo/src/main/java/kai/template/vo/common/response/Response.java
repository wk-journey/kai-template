package kai.template.vo.common.response;

import kai.template.vo.common.base.BaseBean;

/**
 * Created by huluohu on 2016/7/17.
 */
public class Response extends BaseBean {
    //成功或失败
    private boolean     success = true;
    //信息
    private String      msg;
    //扩展码：可以用于错误码
    private Integer     extCode;
    //扩展信息
    private String      extInfo;

    public Response() {
    }

    public Response(boolean success) {
        this.success = success;
    }

    public Response(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Response(boolean success, String msg, String extInfo) {
        this.success = success;
        this.msg = msg;
        this.extInfo = extInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
