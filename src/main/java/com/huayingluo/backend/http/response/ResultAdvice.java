package com.huayingluo.backend.http.response;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.huayingluo.backend.http.constant.HttpCode;
import com.huayingluo.backend.http.exception.BusinessException;
import com.huayingluo.backend.utils.JsonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ResultAdvice implements ResponseBodyAdvice<Object> {


    @Override

    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        return true;

    }


    /**
     * 响应前重写处理
     *
     * @param o                  方法响应返回实体
     * @param methodParameter    方法参数
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest  request
     * @param serverHttpResponse response
     * @return Object        -       重写方法响应返回实体后的对象
     */

    @SneakyThrows

    @Override

    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        return builderResult(o);

    }


    /**
     * 重写方法返回对象响应结构
     *
     * @param o
     * @return
     */

    private Object builderResult(Object o) {

        //如果响应体Result，直接返回，不做处理

        if (o instanceof BaseResult) {

            return o;

        }

        //返回数据data是String的时候需要做出处理，不然会报错

        if (o instanceof String) {

            BaseResult<String> result = ResultBuilder.ok(ResponseStateEnum.success.value, o.toString());

            return JsonUtil.object2Json(result);

        }

        //返回的数据若是异常返回信息，那么不做处理直接返回

        if (o instanceof ResultException) {

            return o;

        }

        return ResultBuilder.ok(ResponseStateEnum.success.value, o);

    }


    /**
     * 先捕获异常 然后再把数据返回到ResponseBody中，
     * <p>
     * 然后在Body中要返回数据的时候调用上面的拦截方法beforeBodyWrite()
     */

    @ExceptionHandler(value = Exception.class)

    public Object handleException(Exception e, HttpServletRequest request) {

        //此处返回json数据

        //捕捉到的异常如果是自定义异常类，那么就返回自定义异常类中的错误码和错误信息

        String stackExceptionMsg = ExceptionUtil.stacktraceToString(e);

        //异常输出到日志

        log.error(stackExceptionMsg);

        //自定义基础异常

        if (e instanceof BusinessException) {

            return new ResultException(((BusinessException) e).getCode(), false, ((BusinessException) e).getMessage(), request.getRequestURL().toString());

            //非法参数异常

        } else if (e instanceof IllegalArgumentException) {

            return new ResultException(HttpCode.BAD_REQUEST.code, false, "参数异常,请稍候再试", request.getRequestURL().toString());

            //绑定异常

        } else if (e instanceof BindException) {

            return new ResultException(HttpCode.BAD_REQUEST.code, false, ((BindException) e).getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURL().toString());

            //方法参数异常验证异常

        } else if (e instanceof MethodArgumentNotValidException) {

            return new ResultException(HttpCode.BAD_REQUEST.code, false, ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURL().toString());

        }


        //这里是除了自定义异常的其他异常信息

        else {

            return new ResultException(HttpCode.SERVER_ERROR.code, false, "系统异常请联系管理员", request.getRequestURL().toString());

        }

    }

}