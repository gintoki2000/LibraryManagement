package com.librarymanagement.utils;

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
            Constructor constructor = null;
            Object config = null;
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
            Object object = entry.getValue();
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Inject.class)) {
                    if (method.getParameterCount() == 1) {
                        Class<?> parameterType = method.getParameterTypes()[0].getClass();
                        Inject inject = method.getAnnotation(Inject.class);
                        Object component = null;
                        if (inject.injectBy() == InjectBy.NAME) {
                            String componentName = inject.componentName().equals("") ? method.getParameters()[0].getName() : inject.componentName();
                            component = getComponent(componentName);
                        }
                        else {
                            component = getComponent(parameterType);
                        }
                        if (component != null) {
                            try {
                                method.invoke(object, component);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(ComponenContainer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex) {
                                Logger.getLogger(ComponenContainer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvocationTargetException ex) {
                                Logger.getLogger(ComponenContainer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else {
                            System.err.println("Container does not contain ");
                        }
                    
                    } else {
                        System.out.println("setter method must have one parameter");
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
