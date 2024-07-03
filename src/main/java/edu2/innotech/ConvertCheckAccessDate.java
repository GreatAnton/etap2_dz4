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
        doStartLog(lldList);
        List<String> errorLog = new ArrayList<>();

        List<LogLoadData> lldListOut = new ArrayList<>();

        for (LogLoadData lld: lldList) {
            if (lld.access_date == null) {
                //System.out.println("ErrorCheckAccessDate: " + lld.username + " " + lld.filename);;
                errorLog.add("ErrorCheckAccessDate: " + lld.username + " " + lld.filename);
                //lldList.remove(lld);
            } else { lldListOut.add(lld); }
        }

        if (errorLog.size() > 0) {
            logPrinter.printLog("LogConvertCheckAccessDate.txt", errorLog);
        }
        doEndLog(lldListOut);
        return lldListOut;
    }
}
