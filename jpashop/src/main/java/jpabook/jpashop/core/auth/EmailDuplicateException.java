package jpabook.jpashop.core.auth;

import jpabook.jpashop.core.handler.CustomVaildationException;
import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{

    private CustomVaildationException errorCode;

    public EmailDuplicateException(String message, CustomVaildationException errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
