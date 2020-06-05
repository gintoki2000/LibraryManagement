package com.librarymanagement.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {

    private final Map<String, Object> components;

    private Object config;

    public AppContext(Class<?> clazz) throws ComponentCreationException, ComponentInjectionException {
        components = new HashMap<>();
        createComponents(clazz);
    }

    public void addComponent(String name, Object component) {
        components.put(name, component);
    }

    public Object getComponent(Class<?> clazz) {
        for (Map.Entry<String, Object> entry : components.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Object getComponent(String componentName) {
        return components.get(componentName);
    }

    public List<Object> getComponents() {
        return new ArrayList<>(components.values());
    }

    private void createComponents(Class<?> clazz) throws ComponentCreationException {
        Method methods[] = clazz.getMethods();
        try {
            Constructor constructor = null;
            for (Constructor c : clazz.getConstructors()) {
                if (c.getParameterCount() == 0) {
                    constructor = c;
                    break;
                }
            }
            config = (Object) constructor.newInstance();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Component.class)) {
                    Object component = method.invoke(config);
                    addComponent(method.getName(), component);
                }
            }
        } catch (Exception e) {
            throw new ComponentCreationException("", e);
        }
    }

    private void injectDependencies() throws ComponentInjectionException {
        for (Map.Entry<String, Object> entry : components.entrySet()) {
            Object object = entry.getValue();
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Inject.class)) {
                    if (method.getParameterCount() == 1) {
                        Class<?> parameterType = method.getParameterTypes()[0].getClass();
                        Inject injectAnotation = method.getAnnotation(Inject.class);
                        Object component = null;

                        component = getComponent(injectAnotation.componentName());

                        if (parameterType.isInstance(component)) {
                            throw new ComponentInjectionException("The dependent type have to same with component type", method ,injectAnotation.componentName());
                        }
                        if (component == null) {
                            throw new ComponentInjectionException("Container does not contain ", method, injectAnotation.componentName());
                        }

                        try {
                            method.invoke(object, component);
                        } catch (Exception ex) {
                            throw new ComponentInjectionException("Failed to inject component ", method ,injectAnotation.componentName());
                        }

                    } else {
                        throw new ComponentInjectionException("method must have only one parameter", method, null);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return components.toString();
    }

    public void refresh() throws ComponentInjectionException {
        injectDependencies();
    }
}
