package com.wzx.demo.handler;

import com.wzx.demo.common.ApiException;
import com.wzx.demo.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.wzx.demo.common.CommonResponse.PARAM_ERROR;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        MethodParameter parameter = ex.getParameter();
        StringBuilder msg = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            msg.append(error.getField()).append(error.getDefaultMessage()).append(";");
        }
        Response response = Response.create(HttpStatus.BAD_REQUEST.toString(), msg.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        Response response = Response.create(status.toString(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOthersException(Exception ex) {
        log.error("param err", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Response response = Response.create(status.toString(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Response response = Response.create(PARAM_ERROR.getCode(), PARAM_ERROR.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleApiServerException(ApiException ex) {
        HttpStatus status = HttpStatus.OK;
        Response response = Response.create(ex.getCode(), ex.getMessage(), ex.getData());
        return new ResponseEntity<>(response, status);
    }
}
