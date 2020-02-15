package kai.template.vo.common.response;

import java.io.Serializable;

/**
 * success response
 */
public class SuccessResponse<T extends Serializable> extends Response {
    private T data;

    public SuccessResponse() {
        super.setSuccess(true);
        super.setMessage("成功");
    }

    public SuccessResponse(String message) {
        super.setSuccess(true);
        super.setMessage(message);
    }

    public SuccessResponse(T data) {
        super.setSuccess(true);
        this.data = data;
    }

    public SuccessResponse(String message, T data) {
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

    public static SuccessResponse newInstance(Serializable data) {
        return new SuccessResponse(data);
    }

    public static SuccessResponse newInstance(String message, Serializable data) {
        return new SuccessResponse(message, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
