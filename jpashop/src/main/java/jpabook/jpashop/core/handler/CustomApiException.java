package jpabook.jpashop.core.handler;

public class CustomApiException  extends  RuntimeException{

    public CustomApiException(String message){
        super(message);
    }
}
