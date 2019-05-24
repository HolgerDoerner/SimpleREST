package com.example;

import java.time.LocalDateTime;
import java.util.Collection;

public class ResponseMessage <T> {
    private String status;
    private LocalDateTime timeStamp;
    private Collection<T> msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Collection<T> getMsg() {
        return msg;
    }

    public void setMsg(Collection<T> msg) {
        this.msg = msg;
    }
}