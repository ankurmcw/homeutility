package com.mcw.homeutility.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by renuka on 30/9/17.
 */

@Configuration
public class AppConfig {

    @Value("${cron.milk.add}")
    private String cronAddMilk;

    @Bean
    public String cronAddMilk() {
        return this.cronAddMilk;
    }
}
