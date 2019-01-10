package com.ycy.entity;

public class BaseRespondBean<T> {
    public int errcode;//错误码
    public String errmsg;//错误信息 当errcode不为0时返回
    public T replydata;//返回的关键数据，每个接口的对象都不一样
    public boolean isSuccess(){
        return errcode == 0;//为0时请求成功
    }
}
