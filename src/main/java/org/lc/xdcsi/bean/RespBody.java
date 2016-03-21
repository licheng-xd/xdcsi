package org.lc.xdcsi.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

/**
 * Created by lc on 15/4/23.
 */
public class RespBody {
    private int code;

    private String msg;

    private Object obj;

    private RespBody(int code) {
        this.code = code;
    }

    private RespBody(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    private RespBody() {

    }

    public int getCode() {
        return code;
    }

    public RespBody setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBody setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBody setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public static RespBody success() {
        return new RespBody(HttpStatus.OK.value());
    }

    public static RespBody builder(HttpStatus status) {
        return new RespBody(status.value());
    }

    @Override public String toString() {
        return JSON.toJSONString(this);
    }
}
