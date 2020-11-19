package com.controller.exception;

public class ParseException extends Exception{
    private static final long serialVersionUID = 1L;

    public ParseException(){
        super();
    }
    public ParseException(String message){
        super(message);
    }
    public ParseException(Exception e){
        super(e);
    }
    public ParseException(String message,Exception e){
        super(message,e);
    }
}
