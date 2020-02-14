package kai.template.web.utils;

import kai.template.exception.ApiError;
import kai.template.utils.json.JsonUtil;
import kai.template.vo.common.response.ErrorResponse;
import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * http响应工具类
 *
 * @author wangkai
 * @date 2020/2/14 12:10 下午
 */
public class HttpResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseUtil.class);

    public static void parseErrorResponse(ApiError apiError, HttpServletResponse response) throws Exception{
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        try(OutputStream outPut = response.getOutputStream()){
            outPut.write(JsonUtil.toJson(ErrorResponse.newInstance(apiError.getCode(), apiError.getDesc())).getBytes(Consts.UTF_8.name()));
            outPut.flush();
        }
    }
}
