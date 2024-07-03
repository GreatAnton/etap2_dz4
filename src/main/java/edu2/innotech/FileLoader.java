package edu2.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileLoader {
    @Autowired
    FileReader fileReader;
    @Autowired
    LoginsRepo loginsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    DataConverter dataConverter;
    @Autowired
    DBLoader dbLoader;

    @Value("${FileLoadDir}")
    private String fileLoadDir;

    public void doFileLoadProcess() {
        loginsRepo.deleteAll();
        usersRepo.deleteAll();

        List<LogLoadData> lldList = fileReader.readFiles(fileLoadDir);
        lldList = dataConverter.doAllConverts(lldList);
        dbLoader.accept(lldList);

        /*
        System.out.println("Users:");
        List<Users> uList = (List<Users>) usersRepo.findAll();
        uList.stream().forEach(System.out::println);
        System.out.println("Logins:");
        List<Logins> lList = (List<Logins>) loginsRepo.findAll();
        lList.stream().forEach(System.out::println);
        */
    }
}
