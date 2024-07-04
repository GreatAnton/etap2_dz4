package edu2.innotech;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LogAnnotationBeanPostProcessor implements BeanPostProcessor {
    Map<String, Object> beans = new HashMap<>();

    List<String> logConvert = new ArrayList<>();
    @Autowired
    LogPrinter logPrinter;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogTransformation.class))
            beans.put(beanName, bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            beans.remove(beanName);
            return doLogConvert(bean);
        }
        return bean;
    }

    @SneakyThrows
    //private List<LogLoadData> doLogConvert(List<LogLoadData> lldlist) {
    private Object doLogConvert(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (!method.getName().equals("doConvert")) return proxy.invokeSuper(obj, args);
            List<LogLoadData> lldlist = (List<LogLoadData>) args[0];
            logConvert.clear();
            logConvert.add("Дата время начала операции конвертации: " +
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            logConvert.add("Название класса компоненты: " + this.getClass().getName());
            logConvert.add("Входные данные: ");
            for (LogLoadData lld : lldlist) {
                logConvert.add(lld.toString());
            }

            List<LogLoadData> lldlistRes = (List<LogLoadData>) proxy.invokeSuper(obj, args);

            logConvert.add("Выходные данные: ");
            for (LogLoadData lld: lldlistRes) {
                logConvert.add(lld.toString());
            }

            LogTransformation logTransformation = bean.getClass().getAnnotation(LogTransformation.class);
            logPrinter.printLog(logTransformation.nameFileLog(), logConvert);

            return lldlistRes;
        });

        return enhancer.create();
    }
}
