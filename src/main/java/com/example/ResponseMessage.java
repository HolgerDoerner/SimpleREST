package com.example;

import java.util.Collection;

public class ResponseMessage <T> {
    private String status;
    private String timeStamp;
    private Integer count;
    private Collection<T> msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Collection<T> getMsg() {
        return msg;
    }

    public void setMsg(Collection<T> msg) {
        this.msg = msg;
    }
}