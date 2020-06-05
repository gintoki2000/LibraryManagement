package com.librarymanagement.utils;

import java.lang.reflect.Method;

public class ComponentInjectionException extends Exception {
    private String componentName;
    private Method method;

    public ComponentInjectionException(String componentName, Method method, String message, Throwable cause) {
        super(message, cause);
        this.componentName = componentName;
        this.method = method;
    }

    public ComponentInjectionException(String componentName, Method method, String message) {
        super(message);
        this.componentName = componentName;
        this.method = method;
    }

    public String getComponentName() {
        return componentName;
    }

    public Method getMethod() {
        return method;
    }
    
    
}
