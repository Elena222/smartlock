package org.snnu.css.dto;

import org.snnu.css.enums.SmartLockEnum;

/**
 * Created by lhy on 2017/1/10.
 */
public class HttpResult {

    //错误代号，状态码
    private int Error_code;
    //发生错误的原因
    private String Reason;

    //泛型，接受各种类型  数据
    private Object result;

    private int state;

    //状态的标识
    private String stateInfo;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getError_code() {
        return Error_code;
    }

    public String getReason() {
        return Reason;
    }

    public void setTranslation(int error_code,String reason,SmartLockEnum statEnum) {
        Error_code = error_code;
        Reason = reason;
        this.state = statEnum.getState();
        this.stateInfo =statEnum.getStateInfo();
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "Error_code=" + Error_code +
                ", Reason='" + Reason + '\'' +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                '}';
    }
}
