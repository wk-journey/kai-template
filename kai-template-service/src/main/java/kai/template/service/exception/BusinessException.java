package kai.template.service.exception;

import kai.template.exception.ApiError;

public class BusinessException extends Exception {
    private int errorCode;
    private String errorMsg;

    public BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ApiError apiError) {
        this.errorCode = apiError.getCode();
        this.errorMsg = apiError.getDesc();
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
