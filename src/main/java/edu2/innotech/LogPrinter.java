package edu2.innotech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class LogPrinter {
    @Value("${LogDir}")
    private String fileLogDir;
    public void printLog(String logFileName, List<String> logList) {
        try {
            File f = new File(fileLogDir + logFileName);
            f.mkdirs();
            if (f.exists()) { f.delete(); }
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            for(String str: logList) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}
