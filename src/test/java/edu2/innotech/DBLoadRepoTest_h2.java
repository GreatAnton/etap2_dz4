package edu2.innotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class DBLoadRepoTest_h2 {
    @Autowired
    DataSource datasource;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    LoginsRepo loginsRepo;

    @BeforeEach
    void setUp() {
        loginsRepo.deleteAll();
        usersRepo.deleteAll();
    }

    @Test
    void testLoadUsers() {
        List<Users> usersList = List.of(
                new Users(null, "user1", "Пользователь Номер Один"),
                new Users(null, "user2", "Пользователь Номер Два")
        );

        usersRepo.saveAll(usersList);
        Assertions.assertEquals(usersRepo.count(), 2);
    }

    @Test
    void testLoadLogins() {
        List<Users> usersList = List.of(
                new Users(null, "user1", "Пользователь Номер Один"),
                new Users(null, "user2", "Пользователь Номер Два")
        );

        usersRepo.saveAll(usersList);

        Assertions.assertEquals(usersRepo.count(), 2);
        Users u1 = usersList.get(0);
        Users u2 = usersList.get(1);

        List<Logins> loginsListList = List.of(
                new Logins(null, Timestamp.valueOf("2024-07-01 05:30:35"), u1, "web"),
                new Logins(null, Timestamp.valueOf("2024-07-01 06:30:35"), u1, "mobile"),
                new Logins(null, Timestamp.valueOf("2024-07-02 05:30:35"), u2, "mobile")
        );

        loginsRepo.saveAll(loginsListList);
        Assertions.assertEquals(loginsRepo.count(), 3);
    }


}
