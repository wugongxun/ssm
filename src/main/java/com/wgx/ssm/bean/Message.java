package com.wgx.ssm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * author:wgx
 * version:1.0
 */
public class Message {
    //状态码，100表示成功，200表示失败
    private Integer code;
    //提示信息
    private String message;
    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<>();

    public static Message success() {
        Message result = new Message();
        result.setCode(100);
        result.setMessage("操作成功");
        return result;
    }
    public static Message fail() {
        Message result = new Message();
        result.setCode(200);
        result.setMessage("操作失败");
        return result;
    }
    public Message add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
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

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
