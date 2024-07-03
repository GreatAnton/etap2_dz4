package edu2.innotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class FileReaderTest {
    @Autowired
    DataSource datasource;
    @Autowired
    FileReader fileReader;

    private String fileLoadDirTest = "./fileLoadDirTest/";

    @Test
    void LoadTestFile() {
        List<LogLoadData> lldListIn = List.of(
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-01 05:30:35"), "web", ""),
                new LogLoadData("user1", "User", "number", "one", Timestamp.valueOf("2024-07-02 06:30:35"), "mobile", "")
        );

        try {
            File f = new File(fileLoadDirTest + "testFile1.txt");
            f.mkdirs();
            if (f.exists()) { f.delete(); }
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            for(LogLoadData lld: lldListIn) {
                writer.write(lld.ToFileLoadStringFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException iex) {
            iex.printStackTrace();
        }

        List<LogLoadData> lldListOut = fileReader.readFiles(fileLoadDirTest);
        Assertions.assertEquals(lldListOut.size(), lldListIn.size());

    }
}
