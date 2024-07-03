package edu2.innotech;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DataConverterable {
    List<LogLoadData> doConvert(List<LogLoadData> lldlist);
}
