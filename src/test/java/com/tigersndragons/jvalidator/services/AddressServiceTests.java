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
    
    @Before
    public void setUp() {
        addressService = new AddressService();
        addressService.setDataSource(new AddressDAO());
    }
    
    @Test
    (expected=NullPointerException.class)
    public void processNullRequestTest(){
        addressService.process(null);
    }
    
    @Test
    (expected=NullPointerException.class)
    public void processPartialRequest(){
        AddressRequest request =  buildTestRequest();
        request.Input_Address=null;
        addressService.process(request);     
    }
    
    @Test
    public void processFullRequest(){

        //when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());
        addressService.setDataSource(new AddressDAO());
        AddressRequest request =  buildTestRequest();
        
        AddressResponse response = addressService.process(request);  
        assertThat(response.getP_Address(), is(equalTo(buildDefaultTestResponse().getP_Address().toUpperCase())));
    }
}
