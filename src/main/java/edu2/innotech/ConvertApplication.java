package edu2.innotech;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(value = 2)
public class ConvertApplication extends SimpleLogger implements DataConverterable {
    @Override
    public List<LogLoadData> doConvert(List<LogLoadData> lldlist) {
        doStartLog(lldlist);

        List<LogLoadData> lldListOut = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.add("web");
        set.add("mobile");

        for (LogLoadData lld: lldlist) {
            if (!set.contains(lld.application)) {
                lld.application = "other:" + lld.application;
            }
            lldListOut.add(lld);
        }
        doEndLog(lldListOut);
        return lldListOut;
    }
}
