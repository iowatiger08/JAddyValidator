package com.tigersndragons.jvalidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import com.tigersndragons.jvalidator.config.AppConfig;


@Configuration
@Import({
	AppConfig.class,	
})
public class TestContextConfiguration {

    @Bean
    public PlatformTransactionManager mockPlatformTransactionManager(){
        return new MockPlatformTransactionManager();
    }
}
