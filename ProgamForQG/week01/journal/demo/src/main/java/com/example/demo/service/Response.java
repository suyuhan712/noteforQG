package com.example.demo.service;

// 泛型响应类，和你 Controller 返回的 Response<StudentDTO> 匹配
public class Response<T> {
    // 必须和前端返回的字段名一致：data、errorMsg、success
    private T data;
    private String errorMsg;
    private boolean success;

    // 无参构造（Spring 序列化需要）
    public Response() {}

    // 全参构造（可选）
    public Response(T data, String errorMsg, boolean success) {
        this.data = data;
        this.errorMsg = errorMsg;
        this.success = success;
    }

    // ✅ 必须生成所有 getter/setter，Jackson 才能序列化字段
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // ✅ 核心：newSuccess 方法必须把传入的 data 赋值给类的 data 字段
    public static <T> Response<T> newSuccess(T data) {
        Response<T> response = new Response<>();
        response.setData(data); // 这行是关键！少了就会 data: null
        response.setSuccess(true);
        response.setErrorMsg(null);
        return response;
    }

    // 可选：失败方法（方便后续扩展）
    public static <T> Response<T> newFail(String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorMsg(errorMsg);
        response.setData(null);
        return response;
    }
}