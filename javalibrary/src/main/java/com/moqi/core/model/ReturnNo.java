package com.moqi.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 图书管理系统的错误码
 * @author Ming Qiu
 */
public enum ReturnNo {
    /***************************************************
     *    系统级返回码
     **************************************************/
    //状态码 200
    OK(0,"成功"),
    CREATED(1, "创建成功"),
    STATENOTALLOW(7,"%s对象（id=%d）%s状态禁止此操作"),
    RESOURCE_FALSIFY(11, "信息签名不正确"),

    //状态码 404
    RESOURCE_ID_NOTEXIST(4,"%s对象(id=%d)不存在"),

    //所有需要登录才能访问的API都可能会返回以下错误
    //状态码 400
    FIELD_NOTVALID(3,"%s字段不合法"),
    IMG_FORMAT_ERROR(8,"图片格式不正确"),
    IMG_SIZE_EXCEED(9,"图片大小超限"),
    INCONSISTENT_DATA(20,"数据不一致"),

    //状态码 401
    AUTH_INVALID_JWT(5,"JWT不合法"),
    AUTH_JWT_EXPIRED(6,"JWT过期"),
    AUTH_INVALID_ACCOUNT(12, "用户名不存在或者密码错误"),
    AUTH_ID_NOTEXIST(13,"登录用户id不存在"),
    AUTH_USER_FORBIDDEN(14,"用户被禁止登录"),
    AUTH_NEED_LOGIN(15, "需要先登录"),

    //状态码 403
    AUTH_NO_RIGHT(16, "无权限"),
    RESOURCE_ID_OUTSCOPE(17,"%s对象(id=%d)超出商铺（id = %d）的操作范围"),
    FILE_NO_WRITE_PERMISSION(18,"目录文件夹没有写入的权限"),

    /***************************************************
     *    图书模块错误码
     **************************************************/
    BOOK_NOT_FOUND(101, "图书(id=%d)不存在"),
    BOOK_ALREADY_EXISTS(102, "图书ISBN(%s)已存在"),
    BOOK_INVALID_FORMAT(103, "图书格式不正确"),
    BOOK_UPDATE_FAILED(104, "更新图书信息失败"),
    BOOK_DELETE_FAILED(105, "删除图书失败"),
    BOOK_API_ERROR(106, "API获取图书失败"),

    /***************************************************
     *    用户模块错误码
     **************************************************/
    USER_NOT_FOUND(201, "用户(id=%d)不存在"),
    USER_INVALID_ACCOUNT(202, "用户名不存在或者密码错误"),
    USER_PERMISSION_DENIED(203, "用户没有权限执行此操作"),

    /***************************************************
     *    借阅模块错误码
     **************************************************/
    BOOK_NOT_AVAILABLE(301, "图书(id=%d)当前不可借阅"),
    BOOK_ALREADY_BORROWED(302, "图书(id=%d)已被借出"),
    BORROW_LIMIT_EXCEEDED(303, "用户已达到借阅限制"),
    BOOK_RETURN_FAILED(304, "图书(id=%d)归还失败"),
    BORROW_RECORD_FAILED(305, "删除借阅记录失败"),
    INVALID_TRANSACTION(306, "无效的借阅记录"),
    TRANSACTION_OVERDUE(307, "已经逾期"),

    /***************************************************
     *    捐赠模块错误码
     **************************************************/
    DONATION_FAILED(401, "捐赠失败"),
    DONATION_NOT_FOUND(402, "捐赠不存在"),

    /***************************************************
     *    系统错误码
     **************************************************/
    INTERNAL_SERVER_ERR(500, "服务器内部错误"),
    PARAMETER_MISSED(501, "缺少必要参数"),
    DATABASE_ERROR(502, "数据库操作失败"),

    /***************************************************
     *    权限模块错误码
     **************************************************/
    AUTH_INVALID_TOKEN(601, "无效的认证令牌"),
    AUTH_EXPIRED_TOKEN(602, "认证令牌过期"),
    AUTH_NO_PERMISSION(603, "无权限访问"),

    /***************************************************
     *    文件模块错误码
     **************************************************/
    FILE_UPLOAD_FAILED(701, "文件上传失败"),
    FILE_FORMAT_ERROR(702, "文件格式不正确"),
    FILE_SIZE_EXCEED(703, "文件大小超限");

    private int errNo;
    private String message;

    private static final Map<Integer, ReturnNo> returnNoMap = new HashMap() {
        {
            for (Object enum1 : values()) {
                put(((ReturnNo) enum1).errNo, enum1);
            }
        }
    };

    ReturnNo(int code, String message){
        this.errNo = code;
        this.message = message;
    }

    public static ReturnNo getByCode(int code1) {
        ReturnNo[] all=ReturnNo.values();
        for (ReturnNo returnNo : all) {
            if (returnNo.errNo == code1) {
                return returnNo;
            }
        }
        return null;
    }

    public static ReturnNo getReturnNoByCode(int code){
        return returnNoMap.get(code);
    }

    public int getErrNo() {
        return errNo;
    }

    public String getMessage() {
        return message;
    }
}