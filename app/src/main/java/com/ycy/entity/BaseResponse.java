package com.ycy.entity;

public class BaseResponse<T> {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAIL = 500;

    private int requestResult;//错误码
    private String errorMsg;//错误信息
    private T obj;//返回的关键数据，每个接口的对象都不一样
    public boolean isSuccess(){
        return requestResult == CODE_SUCCESS;
    }

    public int getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(int requestResult) {
        this.requestResult = requestResult;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
