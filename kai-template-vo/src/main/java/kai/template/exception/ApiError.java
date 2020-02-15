package kai.template.exception;

public enum ApiError {
	SYS_UNSIGNED(-1000, "账户验证失败，请登陆！"),
	SYS_USER_NOT_EXISTS(-1001, "用户不存在"),
	SYS_PERMISSION_DENIED(-1002, "该用户不具有访问权限！"),
	SYS_USER_ERROR(-3, "登录工号或密码错误！"),
	SYS_USER_OFF(-4, "您已离职！"),
	SYS_ROLE_LOCKED(-5, "当前角色下仍有在职员工，不能删除！"),
	SYS_PARAM_NULL(-5, "系统必要参数为空"),
    SYS_PARAM_ERROR(-6, "系统必要参数错误"),
    SYS_EXCEPTION_SERVER(-7, "系统异常"),
    SYS_DATA_NULL(-8,"未找到数据"),

    API_PARAMS_NULL(-10000, "必要参数为空！"),
    ;

	private ApiError(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private int code;
	private String desc;

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
