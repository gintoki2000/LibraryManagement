package com.librarymanagement.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface InjectDependency {
    InjectBy injectBy() default InjectBy.TYPE;
    String dependencyName() default "";
}
