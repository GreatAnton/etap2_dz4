package edu2.innotech;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

//@SpringBootTest
public class PostgreContainerTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
        "postgres:15_alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;

    @BeforeEach
    void setUp() {
        loginsRepo.deleteAll();
        usersRepo.deleteAll();
    }

    //@Test
    void testLoad() {
        List<Users> usersList = List.of(
                new Users(null, "user1", "Пользователь Номер Один"),
                new Users(null, "user2", "Пользователь Номер Два")
        );

        usersRepo.saveAll(usersList);
    }
}
