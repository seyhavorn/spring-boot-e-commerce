package com.seyhavorn.springbootecommerce.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfiguration {
    public void getProperties() {
        System.out.println("Develop environments");
    }
}
