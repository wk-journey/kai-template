package kai.template.exception;

public enum ApiError {
	SYS_UNSIGNED(-1000, "账户验证失败，请登陆！"),
	SYS_USER_NOT_EXISTS(-1001, "用户不存在"),
	SYS_PERMISSION_DENIED(-1002, "该用户不具有访问权限！"),
	SYS_REPEAT_LOGIN(-1003, "该用户在其它地点登录，请确认后重新登录！"),
	SYS_USER_ERROR(-3, "登录工号或密码错误！"),
	SYS_USER_OFF(-4, "您已离职！"),
	SYS_ROLE_LOCKED(-5, "当前角色下仍有在职员工，不能删除！"),
	SYS_PARAM_NULL(-5, "系统必要参数为空"),
    SYS_PARAM_ERROR(-6, "系统必要参数错误"),
    SYS_EXCEPTION_SERVER(-7, "系统异常"),
    SYS_DATA_NULL(-8,"未找到数据"),

    API_PARAMS_NULL(-10000, "必要参数为空！"),
	API_RESULT_NULL(-10100, "接口结果为空！"),
	API_RESULT_NOT_SAME(-10101, "接口结果不一致！"),

	MYSQL_QUERY_NULL(-11000, "mysql查询结果为空！"),
	MYSQL_INSERT_FAIL(-11010, "mysql插入失败！"),
	MYSQL_UPDATE_FAIL(-11020, "mysql更新失败！"),
	MYSQL_DELETE_FAIL(-11030, "mysql删除失败！"),

	REDIS_QUERY_NULL(-12000, "Redis查询结果为空！"),
	REDIS_INSERT_FAIL(-12010, "Redis插入失败！"),
	REDIS_UPDATE_FAIL(-12020, "Redis更新失败！"),
	REDIS_DELETE_FAIL(-12030, "Redis删除失败！"),
	REDIS_EXPIRE_FAIL(-12040, "RedisTTL失败！"),
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
