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
        super.setMsg("失败");
    }

    public ErrorResponse(String msg) {
        super.setSuccess(false);
        super.setMsg(msg);
    }
    public ErrorResponse(Integer extCode, String msg) {
        super.setSuccess(false);
        super.setExtCode(extCode);
        super.setMsg(msg);
    }

    public static ErrorResponse newInstance(){
        return new ErrorResponse();
    }
    public static ErrorResponse newInstance(String msg){
        return new ErrorResponse(msg);
    }
    public static ErrorResponse newInstance(int extCode, String msg){
        return new ErrorResponse(Integer.valueOf(extCode), msg);
    }
}