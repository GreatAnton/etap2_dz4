package edu2.innotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class DataConverterTest {
    @Autowired
    DataSource datasource;
    @Autowired
    List<DataConverterable> dataConverterableList;

    @Autowired
    ApplicationContext ctx;

    @Test
    public void doConvertFIO() {
        List<LogLoadData> lldList = List.of(
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-01 05:30:35"), "web", ""),
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-02 06:30:35"), "mobile", "")
        );

        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);

        ConvertFIO convertFIO = (ConvertFIO) ctx.getBean("convertFIO");
        lldList = convertFIO.doConvert(lldList);

        LogLoadData lld1 = lldList.get(0);
        LogLoadData lld2 = lldList.get(1);

        Assertions.assertEquals(lld1.f, "User");
        Assertions.assertEquals(lld1.i, "Number");
        Assertions.assertEquals(lld1.o, "One");

        Assertions.assertEquals(lld2.f, "User");
        Assertions.assertEquals(lld2.i, "Number");
        Assertions.assertEquals(lld2.o, "One");
    };

    @Test
    public void doConvertApplication() {
        List<LogLoadData> lldList = List.of(
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-01 05:30:35"), "web1", ""),
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-02 06:30:35"), "mobile1", "")
        );

        ConvertApplication convertApplication = (ConvertApplication) ctx.getBean("convertApplication");
        lldList = convertApplication.doConvert(lldList);

        LogLoadData lld1 = lldList.get(0);
        LogLoadData lld2 = lldList.get(1);

        Assertions.assertEquals(lld1.application, "other:web1");
        Assertions.assertEquals(lld2.application, "other:mobile1");
    };

    @Test
    public void doConvertCheckAccessDate() {
        List<LogLoadData> lldList = List.of(
                new LogLoadData("user1", "User", "number", "one", null, "web1", ""),
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-02 06:30:35"), "mobile1", "")
        );

        ConvertCheckAccessDate convertCheckAccessDate = (ConvertCheckAccessDate) ctx.getBean("convertCheckAccessDate");
        lldList = convertCheckAccessDate.doConvert(lldList);

        Assertions.assertEquals(lldList.size(), 1);
    };


}
