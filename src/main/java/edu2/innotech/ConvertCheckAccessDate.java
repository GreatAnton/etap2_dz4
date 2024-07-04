package edu2.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(value = 1)
public class ConvertCheckAccessDate extends SimpleLogger implements DataConverterable {
    @Autowired
    LogPrinter logPrinter;
    @Override
    public List<LogLoadData> doConvert(List<LogLoadData> lldList) {
        List<String> errorLog = new ArrayList<>();

        List<LogLoadData> lldListOut = new ArrayList<>();

        for (LogLoadData lld: lldList) {
            if (lld.access_date == null) {
                errorLog.add("ErrorCheckAccessDate: " + lld.username + " " + lld.filename);
            } else { lldListOut.add(lld); }
        }

        if (errorLog.size() > 0) {
            logPrinter.printLog("LogConvertCheckAccessDate.txt", errorLog);
        }
        return lldListOut;
    }
}
