package com.moqi.core.util;

import com.moqi.core.model.ReturnNo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
    private T data;
    private Integer code;
    private String message;
    private ReturnNo returnNo;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setData(data);
        response.setMessage("成功");
        return response;
    }

    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMessage("成功");
        return response;
    }

    public static <T> Response<T> fail(Integer code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(ReturnNo returnNo, String message) {
        Response<T> response = new Response<>();
        response.setReturnNo(returnNo);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> response = new Response<>();
        response.setMessage(message);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnNo getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(ReturnNo returnNo) {
        this.returnNo = returnNo;
    }
}
