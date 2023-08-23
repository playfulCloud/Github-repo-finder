package com.finder.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class XmlFormatException extends RuntimeException{


    public XmlFormatException(String s){
        super(s);
    }

}
