package kai.template.vo.common.response;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/19
 * Time           :   19:17
 * Description    :
 */
public class ErrorResponse extends Response {
    public ErrorResponse() {
        super.setSuccess(false);
        super.setMessage("失败");
    }

    public ErrorResponse(String message) {
        super.setSuccess(false);
        super.setMessage(message);
    }
    public ErrorResponse(int code, String message) {
        super.setSuccess(false);
        super.setExtCode(code);
        super.setMessage(message);
    }

    public static ErrorResponse newInstance(){
        return new ErrorResponse();
    }

    public static ErrorResponse newInstance(String message){
        return new ErrorResponse(message);
    }

    public static ErrorResponse newInstance(int code, String message){
        return new ErrorResponse(code, message);
    }
}