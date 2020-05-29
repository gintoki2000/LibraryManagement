package com.librarymanagement.utils;

import com.librarymanagement.repositories.ComponentCreationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponenContainer {

    private Map<String, Object> components;

    private Object config;

    public ComponenContainer(Class<?> clazz) throws ComponentCreationException {
        components = new HashMap<>();
        createComponents(clazz);
        injectDependencies();
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
                    components.put(method.getName(), component);
                }
            }
        } catch (Exception e) {
            throw new ComponentCreationException("", e);
        }
    }

    private void injectDependencies() throws ComponentCreationException {
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
                            throw new ComponentCreationException("The dependent type have to same with component type");
                        }
                        if (component == null) {
                            throw new ComponentCreationException("Container does not contain " + injectAnotation.componentName());
                        }

                        try {
                            method.invoke(object, component);
                        } catch (Exception ex) {
                            throw new ComponentCreationException("Failed to inject component " + injectAnotation.componentName());
                        }

                    } else {
                        throw new ComponentCreationException(method.getName() + " method must have one parameter");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return components.toString();
    }

}
