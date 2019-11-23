package com.coffeeshop.configuration;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DatabaseHealthCheckConfigurationPostProcessor implements BeanPostProcessor {

    private static final int SECONDS_TO_WAIT = 10;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            DataSource ds = (DataSource) bean;
            int count = 5;

            while (!isMySQLUp(ds) && count != 0) {
                log.warn("Waiting for {} seconds for the next attempt", SECONDS_TO_WAIT);
                TimeUnit.SECONDS.sleep(SECONDS_TO_WAIT);
                count--;
            }
        }
        return bean;
    }

    private boolean isMySQLUp(DataSource dataSource) {
        try {
            dataSource.getConnection();
            return true;
        } catch (SQLException e) {
            log.warn("Database server is still starting and not available yet");
            return false;
        }
    }
}
