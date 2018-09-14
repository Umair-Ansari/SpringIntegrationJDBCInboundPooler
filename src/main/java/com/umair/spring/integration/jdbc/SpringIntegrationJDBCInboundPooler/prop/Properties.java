package com.umair.spring.integration.jdbc.SpringIntegrationJDBCInboundPooler.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author m.umair
 */
@Component
public class Properties {

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driverClassName;
    
    @Value(value = "${spring.datasource.url}")
    private String url;
    
    @Value(value = "${spring.datasource.username}")
    private String userName;
    
    @Value(value = "${spring.datasource.password}")
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
