package com.tigersndragons.jvalidator.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.tigersndragons.jvalidator.BaseTestCase;
import com.tigersndragons.jvalidator.dao.AddressDAO;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;

public class AddressServiceTests extends BaseTestCase {

    private AddressService addressService;
    private AddressDAO addressDAO;
    
    @Before
    public void setUp() throws Exception {
        addressService = new AddressService();
        addressService.setDataSource(addressDAO);
    }
    
    @Test
    (expected=NullPointerException.class)
    public void processNullRequestTest(){
        addressService.process(null);
    }
    
    @Test
    (expected=NullPointerException.class)
    public void processPartialRequest(){
        AddressRequest request =  BuildTestRequest();
        request.Input_Address=null;
        addressService.process(request);     
    }
    
    @Test
    public void processFullRequest(){

        //when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());
        addressService.setDataSource(new AddressDAO());
        AddressRequest request =  BuildTestRequest();
        
        AddressResponse response = addressService.process(request);  
        assertThat(response.getP_Address(), is(equalTo(BuildDefaultTestResponse().getP_Address().toUpperCase())));
    }
    

    private AddressRequest BuildTestRequest(){
        AddressRequest request = new AddressRequest();     
        request.Input_Address = BuildDefaultTestResponse().getP_Address();
        request.Input_City = BuildDefaultTestResponse().getP_City();
        request.Input_Country = "US";
        request.Input_State = BuildDefaultTestResponse().getP_State();
        request.Input_Zip = BuildDefaultTestResponse().getP_Zip();
        request.Input_Zip4 = BuildDefaultTestResponse().getP_Zip4();
        return request;
    }
    private AddressResponse BuildDefaultTestResponse(){
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
}
