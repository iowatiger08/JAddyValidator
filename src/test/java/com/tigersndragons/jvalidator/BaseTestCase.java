package com.tigersndragons.jvalidator;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
		TestContextConfiguration.class
})
@WebAppConfiguration
@Transactional("mockPlatformTransactionManager")
public abstract class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests{
	
}
