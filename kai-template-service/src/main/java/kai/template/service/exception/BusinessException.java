package kai.template.service.exception;

public class BusinessException extends Exception {
    private int errorCode;
    private String errorMsg;

    public BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
