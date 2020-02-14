package kai.template.exception;

public enum ApiError {
	SYS_UNSIGNED(-1, "账户验证失败，请重新登陆！"),
	SYS_PERMISSION_DENIED(-2, "该用户不具有访问权限！"),
	SYS_USER_ERROR(-3, "登录工号或密码错误！"),
	SYS_USER_OFF(-4, "您已离职！"),
	SYS_ROLE_LOCKED(-5, "当前角色下仍有在职员工，不能删除！"),
	SYS_PARAM_NULL(-5, "系统必要参数为空"),
    SYS_PARAM_ERROR(-6, "系统必要参数错误"),
    SYS_EXCEPTION_SERVER(-7, "系统异常"),
    SYS_DATA_NULL(-8,"未找到数据"),
    SYS_COMMON_EXCEPTION(-10000, "通用业务异常"),
    STOCK_INLET_NULL(-10000,"出错啦，赶快联系妈妈好客服吧！#0411"),
    STOCK_INLET_OUT(-10000,"出错啦，赶快联系妈妈好客服吧！#0412"),
    STOCK_C_SHOP_ID_NULL(-10000,"出错啦，赶快联系妈妈好客服吧！#0408"),
	STOCK_C_ITEM_ID_NULL(-10000,"出错啦，赶快联系妈妈好客服吧！#0409"),
	STOCK_C_TEMPLATE_ID_NULL(-10000,"出错啦，赶快联系妈妈好客服吧！#0410"),
	STOCK_TEMPLATE_NOT_ITEM(-10000,"出错啦，该款式还没有此商品！#0424"),
	MMH_USER_SERVER_ERROR(-1000,"user用户微服务异常"),
	MMH_GOODS_SERVER_ERROR(-1001,"goods商品微服务异常"),

	/********************  POS会员用户 **************************/
	POS_VIP_USER_NOT_EXISTS(-10000, "用户不存在"),
	POS_VIP_USER_CARD_NOT_EXISTS(-10000, "卡号不能为空"),
	POS_VIP_USER_CARD_PWD_NOT_EXISTS(-10000, "密码不能为空"),
	POS_VIP_USER_EXCEPTION(-10000, "请确保用户信息正确，散客用户目前仅支持上门自提"),

	/********************  POS订单结算 **************************/
	ORDER_NOT_EXISTS(-10000,"订单不存在"),
	ORDER_LINE_PAY_NOT_EXISTS(-10000,"订单商品支付信息不存在"),
	ORDER_STATE_ERROR(-10000,"订单状态不匹配"),
	ORDER_STATE_NOT_HANGUP(-10000,"订单非挂起状态无法废弃"),
	TIME_CARD_NOT_ENOUGH(-10000,"时间卡余额不足"),

	/********************  售后 **************************/
	REFUND_PAY_TYPE_ERROR(-10000,"退款方式错误"),
	REFUND_PAY_AMOUNT_ERROR(-10000,"退款金额有误"),
	REFUND_PAY_AMOUNT_MAX_ERROR(-10000,"退款金额超出支付金额"),
	REFUND_PAY_ORDERLINE_NULL(-10000,"订单商品行不存在"),
	REFUND_CARD_ERROR(-10000,"退卡后激活的卡项不允许再退卡"),
	REFUND_CARD_NOT_EXIST(-10000,"卡项不存在"),
	REFUND_CARD_TYPE_ERROR(-10000,"该卡项不能退款"),
	REFUND_CARD_NULL(-10000,"卡项已经全部失效或已经花完，不能退款"),
	REFUND_CARD_UNABLE(-10000,"对应的卡项已发生退卡，不能退款"),
	REFUND_TIME_CARD_UNABLE(-10000,"时间卡购买的服务不支持退款"),
	REFUND_UNION_PAY_UNSUPPORT(-10000,"退款暂不支持银联"),
	REFUND_REPEAT_UNSUPPORT(-10000,"同一商品暂不支持退多次"),
	REFUND_CARD_TYPE_NOTOPERATE(-10000,"非储值卡不能升卡"),
	NOPAY_CARD_TYPE_NOTOPERATE(-10000,"欠款不能升卡"),

	NO_COUNT_FOR_EXCHANGE(-10000,"该商品行已经没有可换数量了"),
	EXCHANGE_NOT_EXIST(-10000,"换货单不存在"),
	NO_STOCK_FOR_EXCHANGE(-10000,"换货商品没有库存"),

	/********************  优惠券 **************************/
	VOUCHER_NOT_EXISTS(-10000,"优惠券不存在"),
	VOUCHER_TEMPLATE_NOT_EXISTS(-10000,"优惠券模版不存在"),
	VOUCHER_HAS_USED(-10000,"优惠券已经被使用过"),
	VOUCHER_USE_CHECK_PASS(-10000,"优惠券不符合使用条件"),
	VOUCHER_NOT_EXISTS_CARD(-10000,"请先选择会员"),
	VOUCHER_USER_NOT_EXISTS(-10000,"会员不存在"),
	VOUCHER_QUERY_NOT_EXISTS(-10000,"优惠券不存在或已被使用"),
	PAY_TYPE_HAS_NOT_USE_VOUCHER(-10000,"该支付方式不能使用优惠券"),

	/******************** 支付 **************************/
	PRICE_NOT_MATCH(-10000,"支付金额不一致"),
	PWD_ERROR(-10000,"密码不正确"),
	VALUE_OUT(-5678,"余额不足"),	// 该业务枚举异常，有逻辑处理，请勿随意改动此枚举值信息，-5678
	REFUND_VALUE_OUT(-10000,"退款金额过大"),
	CARD_DETAIL_NOT_EXISTS(-10000,"卡项明细不存在"),
	CARD_NOT_EXISTS(-10000,"卡项不存在"),
	CARD_TEMPLATE_NOT_EXISTS(-10000,"卡项模板不存在"),
	CARD_PAY_PARAM_ERROR(-10000,"卡项支付参数错误"),
	NO_CAN_BE_USED_CARD(-10000,"没有符合条件的卡项能使用"),
	NO_CARD_CAN_USE_ERROR(-10000,"没有符合条件的卡项，请选择其它支付方式"),
	PAY_CODE_EXPIRE(-10000,"付款码已过期"),
	UPDATE_NEGOTIATE_PRICE_FAILURE(-10000, "更新议价额度失败"),
	ORDER_TRANSFER_DEPOSIT_ERROR(-10000, "寄存单不能转为寄存单"),

	/******************** 评价 **************************/
	SHOP_NOT_EXIST_COMMENT(-100,"当前门店无服务评价"),
	SHOP_NOT_OPEN_COMMENT(-101,"当前门店未开启评价"),
	SAVE_COMMENT_EMPTY(-102,"保存评价信息为空"),

	/******************** 小票设置 **************************/
	RECEIPT_PORT_CONFIG_NULL(-200,"小票端口未设置"),
	RECEIPT_PORT_CONFIG_UNUSED(-201,"小票端口未启用"),

	/******************** 预约功能 **************************/
	APPOINTMENT_MEMBER_NOT_EXISTS(-300, "会员不存在"),
	APPOINTMENT_RECORD_NOT_EXISTS(-301, "预约记录不存在"),
	APPOINTMENT_SHOP_NOT_EXISTS(-302, "店铺不存在"),
	APPOINTMENT_TIME_SEGMENT_NOT_EXISTS(-303, "时间段不存在"),
	APPOINTMENT_STAFF_NOT_EXISTS(-304, "员工不存在"),
	APPOINTMENT_STAFF_COUNT_LIMIT(-305, "员工时间段已约满"),
	APPOINTMENT_TIME_SEGMENT_COUNT_LIMIT(-306, "该时间段已约满"),
	APPOINTMENT_DATE_NOT_APPOINTMENT(-307, "预约次数已达上限，无法预约"),
	APPOINTMENT_ORDER_PARAMETER_ERROR(-308, "订单参数错误"),
	APPOINTMENT_APPOINT_PARAMETER_ERROR(-309, "预约参数错误"),

	/******************** 卡项相关 **************************/
	SYS_CLOSE_ENABLE(-10000,"总店已禁用"),
	CARD_STATUS_IS_NOT_CALC(-10000,"卡项当前状态不能被处理"),

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
