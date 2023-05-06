package com.huayingluo.backend.entity;

import com.huayingluo.backend.http.response.BaseResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class EntityValidator {
    static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();

    public static <T> BaseResult validate(T t) {

        BaseResult baseResult = BaseResult.success();

        Set<ConstraintViolation<T>> validate = validator.validate(t);

        if (validate.size() > 0) {
            String error = "";
            for (ConstraintViolation<T> item : validate) {
                error += item.getMessage() + "<br/>";
            }
            baseResult = BaseResult.fail(error);
        }
        return baseResult;

        // Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        // return constraintViolations;
    }
}