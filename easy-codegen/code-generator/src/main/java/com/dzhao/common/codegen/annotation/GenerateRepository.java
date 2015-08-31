package com.dzhao.common.codegen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GenerateRepository {
    public String modelPackageName() default "";
    public String repositoryPackageName() default "";
    public String modelSimpleName() default "";
    public String simpleName() default "";
    public String target() default "target/generated-sources/apt/";
}
