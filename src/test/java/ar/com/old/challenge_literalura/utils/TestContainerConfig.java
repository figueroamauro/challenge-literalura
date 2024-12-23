package ar.com.old.challenge_literalura.utils;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
public class TestContainerConfig {

    private static final MySQLContainer<?> mysqlContainer =
            new MySQLContainer<>("mysql:8.0.33")
                    .withUsername("test")
                    .withPassword("test")
                    .withDatabaseName("test");

    static {
        mysqlContainer.start();
        System.setProperty("spring.datasource.url", mysqlContainer.getJdbcUrl());
    }

}
