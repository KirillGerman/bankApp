package ru.meshgroup.bankApplication.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import java.util.Properties;


@Slf4j
public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

    private  PostgreSQLContainer<?> postgres;
    private final static Properties config = new Properties();
    private final static String PROPERTIES = "application.yaml";
    private static String DB_NAME;

    static {
        try {
            config.load(PostgreSQLExtension.class.getClassLoader().getResourceAsStream(PROPERTIES));
            DB_NAME = config.getProperty("url");
            if (DB_NAME == null) {
                throw new RuntimeException();
            }
            DB_NAME = DB_NAME.substring(DB_NAME.lastIndexOf("/") + 1);
        } catch (Exception e) {
            log.error("Ошибка чтения application.yaml");
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) {

        postgres = new PostgreSQLContainer<>("postgres:13")
                .withExposedPorts(5432)
                .withDatabaseName(DB_NAME)
                .withUsername(config.getProperty("username"))
                .withPassword(config.getProperty("password"));

        postgres.start();
        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/%s", postgres.getFirstMappedPort(), DB_NAME);
        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", config.getProperty("username"));
        System.setProperty("spring.datasource.password", config.getProperty("password"));

        log.info("spring.datasource.url {}", jdbcUrl);

    }

    @Override
    public void afterAll(ExtensionContext context) {

    }
}
