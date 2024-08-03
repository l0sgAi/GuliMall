package com.losgai.gulimall.product.exception;

import com.losgai.gulimall.common.common.exception.BaseCodeEnume;
import com.losgai.gulimall.common.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

//统一处理异常
@Slf4j
@RestControllerAdvice(basePackages = "com.losgai.gulimall.product.controller")
public class GuliMallExceptionAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidateException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{},异常类型:{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> hashMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            //获取错误提示
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            hashMap.put(field, defaultMessage);
        });
        Result result = new Result().error(BaseCodeEnume.VALID_EXCEPTION.getCode(), BaseCodeEnume.VALID_EXCEPTION.getMsg());
        result.setData(hashMap);
        return result;
    }

    @ExceptionHandler(value = Throwable.class)
    public Result handleException(Throwable throwable) {
        log.error("错误", throwable);
        return new Result().error(BaseCodeEnume.UNKNOWN_EXCEPTION.getCode(), BaseCodeEnume.UNKNOWN_EXCEPTION.getMsg());
    }

}
