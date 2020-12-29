package com.testJugRu.TestJugRu;

import lombok.Setter;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private List<ObjectConfigurator> configurators;
    private ApplicationContext context;

    @SneakyThrows
    ObjectFactory(ApplicationContext context) {
        this.context = context;
        configurators = new ArrayList<>();
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = implClass.getDeclaredConstructor().newInstance();
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));

        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }

        if (implClass.isAnnotationPresent(Deprecated.class)) {
            return (T) Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("*************** чтож ты делаешь?");
                    return method.invoke(t, args);
                }
            });
        }

        return t;
    }
}
