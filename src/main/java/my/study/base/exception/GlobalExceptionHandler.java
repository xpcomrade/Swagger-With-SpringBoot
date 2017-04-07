package my.study.base.exception;

import my.study.base.model.CommonJsonResult;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xpcomrade on 2016/9/12.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (异常处理). <br/>
 */
@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler{

    @ExceptionHandler(value = { Exception.class, ProcessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonJsonResult unknownException(Exception ex, WebRequest req) {
        CommonJsonResult jsonResult = new CommonJsonResult();
        if (ex instanceof ProcessException) {
            ProcessException e = (ProcessException) ex;
            jsonResult.setFailure(e.getCode(), e.getMessage());
        }
        else if (ex instanceof HttpRequestMethodNotSupportedException) {
            jsonResult.setFailure(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());
        }
        else if (ex instanceof HttpMediaTypeNotSupportedException) {
            jsonResult.setFailure(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        }
        else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            jsonResult.setFailure(HttpServletResponse.SC_NOT_ACCEPTABLE, ex.getMessage());
        }
        else if (ex instanceof MissingPathVariableException) {
            jsonResult.setFailure(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        else if (ex instanceof MissingServletRequestParameterException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof ServletRequestBindingException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof ConversionNotSupportedException) {
            jsonResult.setFailure(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        else if (ex instanceof TypeMismatchException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof HttpMessageNotReadableException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof HttpMessageNotWritableException) {
            jsonResult.setFailure(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        else if (ex instanceof MethodArgumentNotValidException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof MissingServletRequestPartException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof BindException) {
            jsonResult.setFailure(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
        else if (ex instanceof NoHandlerFoundException) {
            jsonResult.setFailure(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
        }
        else {
            jsonResult.setFailure(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        }

        return jsonResult;
    }

}
