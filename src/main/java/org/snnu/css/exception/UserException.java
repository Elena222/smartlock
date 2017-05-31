package org.snnu.css.exception;

/**
 * 用户操作异常
 * Created by lhy on 2017/1/5.
 */
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
