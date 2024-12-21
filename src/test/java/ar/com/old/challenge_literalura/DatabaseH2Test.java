package ar.com.old.challenge_literalura;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseH2Test {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testH2Database() {
        assertNotNull(dataSource);
        System.out.println("DataSource: " + dataSource);
    }
}
