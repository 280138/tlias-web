package com.th3angrycalf.exception;

public class ClazzHasStudentException extends RuntimeException{

    public ClazzHasStudentException(){}

    public ClazzHasStudentException(String message){
        super(message);
    }
}
