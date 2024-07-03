package edu2.innotech;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SimpleLogger {
    List<String> logConvert = new ArrayList<>();
    @Autowired
    LogPrinter logPrinter;
    public void doStartLog(List<LogLoadData> lldlist) {
        if (this.getClass().isAnnotationPresent(LogTransformation.class)) {
            logConvert.clear();
            logConvert.add("Дата время начала операции конвертации: " + System.currentTimeMillis());
            logConvert.add("Название класса компоненты: " + this.getClass().getName());
            logConvert.add("Входные данные: ");
            for (LogLoadData lld : lldlist) {
                logConvert.add(lld.toString());
            }
        }
    }

    public void doEndLog(List<LogLoadData> lldlist) {
        if (this.getClass().isAnnotationPresent(LogTransformation.class)) {
            logConvert.add("Выходные данные: ");
            for (LogLoadData lld: lldlist) {
                logConvert.add(lld.toString());
            }

            LogTransformation logTransformation = this.getClass().getAnnotation(LogTransformation.class);
            logPrinter.printLog(logTransformation.nameFileLog(), logConvert);
        }
    }

}
