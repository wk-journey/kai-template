package kai.template.vo.common.response;

import java.io.Serializable;

/**
 * success response
 */
public class SuccessResponse extends Response {
    private Object data;

    public SuccessResponse() {
        super.setSuccess(true);
        super.setMessage("成功");
    }

    public SuccessResponse(String message) {
        super.setSuccess(true);
        super.setMessage(message);
    }

    public SuccessResponse(Object data) {
        super.setSuccess(true);
        this.data = data;
    }

    public SuccessResponse(String message, Object data) {
        super.setSuccess(true);
        super.setMessage(message);
        this.data = data;
    }

    public static SuccessResponse newInstance() {
        return new SuccessResponse();
    }

    public static SuccessResponse newInstance(String message) {
        return new SuccessResponse(message);
    }

    public static SuccessResponse newInstance(Object data) {
        return new SuccessResponse(data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
