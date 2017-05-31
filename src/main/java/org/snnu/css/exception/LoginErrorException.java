package org.snnu.css.exception;

/**
 * Created by lhy on 2017/1/5.
 */
public class LoginErrorException extends RuntimeException{

        public LoginErrorException(String message) {
            super(message);
        }

        public LoginErrorException(String message, Throwable cause) {
            super(message, cause);
        }


}
