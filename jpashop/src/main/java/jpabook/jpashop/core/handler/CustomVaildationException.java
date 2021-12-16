package jpabook.jpashop.core.handler;

import lombok.AllArgsConstructor;

import java.util.Map;


public class CustomVaildationException extends RuntimeException{


    private Map<String ,String> errorMap;

    public CustomVaildationException(String message){
        super(message);
    }

    public CustomVaildationException(String message , Map<String , String> errorMap){
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String , String> getErrorMap(){
        return errorMap;
    }


}
