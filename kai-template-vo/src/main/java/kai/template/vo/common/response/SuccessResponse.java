package kai.template.vo.common.response;

import java.io.Serializable;

/**
 * success response
 */
public class SuccessResponse<T extends Serializable> extends Response {
    private T data;
    public SuccessResponse() {
        super.setSuccess(true);
        super.setMsg("成功");
    }
    public SuccessResponse(String msg){
        super.setSuccess(true);
        super.setMsg(msg);
    }
    public SuccessResponse(T data){
        super.setSuccess(true);
        this.data = data;
    }

    public static SuccessResponse newInstance(){
        return new SuccessResponse();
    }
    public static SuccessResponse newInstance(String msg){
        return new SuccessResponse(msg);
    }
    public static SuccessResponse newInstance(Serializable data){
        return new SuccessResponse(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
