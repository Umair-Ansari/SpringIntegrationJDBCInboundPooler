package com.umair.spring.integration.jdbc.SpringIntegrationJDBCInboundPooler.config;

import com.umair.spring.integration.jdbc.SpringIntegrationJDBCInboundPooler.prop.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author m.umair
 */
@Configuration
public class Config {

    @Autowired
    private Properties properties;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @InboundChannelAdapter(channel = "fromdb", poller = @Poller(fixedDelay = "4000"))
    public MessageSource<?> counterMessageSource() {
        return new JdbcPollingChannelAdapter(dataSource(), "select * from configuration");
    }

    @Bean
    @ServiceActivator(inputChannel = "fromdb")
    public MessageHandler resultFileHandler() {
        return (Message<?> message) -> {
            System.err.println(message.getPayload());
        };
    }

}
