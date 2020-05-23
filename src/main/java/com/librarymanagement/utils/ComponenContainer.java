package com.librarymanagement.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponenContainer {

    private Map<String, Object> components;

    public ComponenContainer(Class<?> clazz) {
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

    private void createComponents(Class<?> clazz) {
        Method methods[] = clazz.getMethods();
        try {
            Object config = (Object) clazz.newInstance();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Component.class)) {
                    Object component = method.invoke(config);
                    components.put(method.getName(), component);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void injectDependencies() {
        for (Map.Entry<String, Object> entry : components.entrySet()) {
            Method[] methods = entry.getValue().getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(InjectDependency.class) && method.getParameterCount() == 1) {
                    try {
                        InjectDependency injectDependency = method.getAnnotation(InjectDependency.class);
                        if (injectDependency.injectBy() == InjectBy.TYPE) {
                            method.invoke(entry.getValue(), getComponent(method.getParameterTypes()[0]));
                        }
                        else {
                            method.invoke(entry.getValue(), getComponent(injectDependency.dependencyName()));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
