package com.suncreate.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 接口返回值定义
 *
 * @author egoo
 * @date 2019/3/11
 */
@Data
public class ApiResponse {
    private Integer code;
    private String status;
    private String message;
    private String traceID;
    private Long  currentTime;//获取当前服务器时间
    private Object data;
    private Object attach;

    public ApiResponse() {
        init200Response();
    }

    public void init200Response(){
        this.setCode(HttpStatus.OK.value());
        this.setStatus("success");
    }

    public void init404Response() {
        this.setCode(HttpStatus.NOT_FOUND.value());
        this.setStatus("fail");
    }

    public void init50XResponse() {
        this.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setStatus("error");
        this.setMessage("服务器错误");
    }

    public void init203Response() {
        this.setCode(203);
        this.setStatus("invalid params");
    }
}
