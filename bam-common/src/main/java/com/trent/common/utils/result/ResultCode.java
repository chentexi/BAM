package com.trent.common.utils.result;

import lombok.Getter;

@Getter
    public enum ResultCode implements StatusCode{
    SUCCESS(200, "操作成功"),
    FAILED(1001, "操作失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败");

    private int code;
    private String msg;
    ResultCode( int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
