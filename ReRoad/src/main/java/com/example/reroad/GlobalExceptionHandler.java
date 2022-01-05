package com.example.reroad;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(final RuntimeException e) {
        Exception e2 = e;
        e2.printStackTrace();

        return "error/exception";
    }

    // 유효성 검증 실패시 예외처리(메시지출력)
    @ExceptionHandler({BindException.class})
    public String processValidationError(BindException e){

        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append("하지만 입력된 값은: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]              이네요^_^");
        }

        return  builder.toString();
    }
}