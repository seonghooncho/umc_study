package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import umc.study.validation.validator.CheckPageValidator;

import java.lang.annotation.*;

@Documented//사용자 정의 어노테이션
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })//적용 범위
@Retention(RetentionPolicy.RUNTIME)//생명 주기
public @interface CheckPage {
}
