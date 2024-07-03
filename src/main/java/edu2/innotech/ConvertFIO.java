package edu2.innotech;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@LogTransformation(nameFileLog = "LogConvertFIO")
@Order(value = 2)
public class ConvertFIO extends SimpleLogger implements DataConverterable {

    private String upper1(String str) {
        String s1 = str.substring(0, 1);
        s1 = s1.toUpperCase();
        String s2 = str.substring(1, str.length());
        return s1 + s2;
    }
    @Override
    public List<LogLoadData> doConvert(List<LogLoadData> lldlist) {
        doStartLog(lldlist);
        List<LogLoadData> lldListOut = new ArrayList<>();
        for (LogLoadData lld: lldlist) {
            lld.f = upper1(lld.f);
            lld.i = upper1(lld.i);
            lld.o = upper1(lld.o);
            lldListOut.add(lld);
        }
        doEndLog(lldListOut);
        return lldListOut;
    }
}
