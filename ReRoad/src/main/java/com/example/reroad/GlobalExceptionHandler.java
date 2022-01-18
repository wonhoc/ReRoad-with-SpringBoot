package com.example.reroad;

import com.example.common.ValidErrVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(final RuntimeException e) {
        Exception e2 = e;
        e2.printStackTrace();
        ModelAndView model = new ModelAndView();

        model.addObject("exception",e2);
        model.setViewName("views/error/error");

        return model;
    }

    // 유효성 검증 실패시 예외처리(메시지출력)
    @ExceptionHandler({BindException.class})
    public String processValidationError(BindException e, Model model){

        BindingResult bindingResult = e.getBindingResult();
        List<ValidErrVO> fieldErrorList = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ValidErrVO err = new ValidErrVO();
            err.setField(fieldError.getField());
            err.setMessage(fieldError.getDefaultMessage());
            err.setRejectedValue(fieldError.getRejectedValue().toString());
            fieldErrorList.add(err);
        }
        model.addAttribute("fieldErrorList",fieldErrorList);
        return "views/error/valid-exception";
    }

    //파일 업로드 예외처리(파일 최대 사이즈 초과!)
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public String handleMaxUploadSizeExceededException(){

        return "views/error/fileException";
    }
}