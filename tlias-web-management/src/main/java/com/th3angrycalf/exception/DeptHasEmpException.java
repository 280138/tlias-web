package com.th3angrycalf.exception;

public class DeptHasEmpException extends RuntimeException{
    public DeptHasEmpException(){
    }
    public DeptHasEmpException(String message){
        super(message);
    }
}
