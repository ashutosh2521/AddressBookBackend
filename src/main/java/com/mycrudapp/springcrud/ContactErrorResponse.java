package com.mycrudapp.springcrud;

public class ContactErrorResponse
{
    private int status;
    private String message;
    private long time;

    public ContactErrorResponse() {
    }

    public ContactErrorResponse(int status, String message, long time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }
}
