package edu2.innotech;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileReader {
    public List<LogLoadData> readFiles(String patchDir) {

        List<LogLoadData> lldList = new ArrayList<>();
        try {
            File fs = new File(patchDir);
            for (File file : fs.listFiles()) {
                //System.out.println("Начинаем разбор файла " + file.getName());
                Scanner sc = new Scanner(new FileInputStream(file));

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (!line.isEmpty()) {
                        //System.out.println(line);
                        // Логин Фамилия Имя Отчество дата_входа тип_приложения
                        LogLoadData lld = new LogLoadData();

                        String[] lineArr = line.split(" ");
                        lld.username = lineArr[0];
                        lld.f = lineArr[1];
                        lld.i = lineArr[2];
                        lld.o = lineArr[3];
                        try {
                            String access_date_str = lineArr[4] + " " + lineArr[5];
                            lld.access_date = Timestamp.valueOf(access_date_str);
                        } catch (Exception ex) {
                            lld.access_date = null;
                        }
                        lld.application = lineArr[6];
                        lld.filename = file.getName();

                        lldList.add(lld);

                        //System.out.println(lld);
                    }

                }
                sc.close();
            }
        }
        catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        catch (Exception ex) {
            //ex.printStackTrace();
            throw new IllegalArgumentException(ex);
        }
        return lldList;
    }
}
