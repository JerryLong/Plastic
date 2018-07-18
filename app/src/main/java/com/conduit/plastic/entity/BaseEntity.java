package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by android on 2017/3/8.
 */

public class BaseEntity<T> implements Serializable {

    /**
     * errorCode : xx
     * errors : ["yyy"]
     * error : aaa
     * data : zzz
     */

    public int errorCode;
    public String error;
    public T data;
    public List<String> errors;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
