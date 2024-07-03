package edu2.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataConverter {
    @Autowired
    List<DataConverterable> dataConverterableList;

    public List<LogLoadData> doAllConverts(List<LogLoadData> lldlist) {
        for (DataConverterable dc: dataConverterableList) {
            lldlist = dc.doConvert(lldlist);
            //System.out.println(dc.getClass().getName());
        }
        return lldlist;
    };
}
