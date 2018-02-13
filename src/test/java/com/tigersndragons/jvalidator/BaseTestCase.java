package com.tigersndragons.jvalidator;

import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;
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

	public AddressResponse buildDefaultTestResponse(){
		Double latitude = 41.58751;
		Double longitude = -93.62614;
		AddressResponse anAddress = new AddressResponse();
		anAddress.setP_Address("666 Grand Ave");
		anAddress.setP_City ( "Des Moines");
		anAddress.setP_ErrorCode ( "");
		anAddress.setP_ErrorMsg ( "");
		anAddress.setP_Latitude ( latitude);
		anAddress.setP_Longitude ( longitude);
		anAddress.setP_Timezone ( "C");
		anAddress.setP_State ( "IA");
		anAddress.setP_Zip ( "50309");
		anAddress.setP_Zip4 ( "2506");
		anAddress.setP_Country ( "US");

		return anAddress;
	}

	public AddressRequest buildTestRequest(){
		AddressRequest request = new AddressRequest();
		request.Input_Address = buildDefaultTestResponse().getP_Address();
		request.Input_City = buildDefaultTestResponse().getP_City();
		request.Input_Country = "US";
		request.Input_State = buildDefaultTestResponse().getP_State();
		request.Input_Zip = buildDefaultTestResponse().getP_Zip();
		request.Input_Zip4 = buildDefaultTestResponse().getP_Zip4();
		return request;
	}
}
