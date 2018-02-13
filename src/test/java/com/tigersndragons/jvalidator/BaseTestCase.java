package com.tigersndragons.jvalidator;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tigersndragons.jvalidator.config.AppConfig;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
		TestContextConfiguration.class
})
@WebAppConfiguration
@Transactional("mockPlatformTransactionManager")
public abstract class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests{
	
}
