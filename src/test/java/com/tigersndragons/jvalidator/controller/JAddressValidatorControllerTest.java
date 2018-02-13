package com.tigersndragons.jvalidator.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tigersndragons.jvalidator.BaseTestCase;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;
import com.tigersndragons.jvalidator.services.IAddressService;

public class JAddressValidatorControllerTest extends BaseTestCase{

    private JAddressValidatorController controller;
    public static String ALL_FIELDS_REQUIRED="All fields required request.";
    //private String DefaultGetUrlwParams = "Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US";
            //"http://dev01/Address/index/new-client?Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US"
    @Mock
    private IAddressService<AddressRequest,AddressResponse> mockAddressService ;

	@Before
	public void setUp()  {
	    MockitoAnnotations.initMocks(this);
	    controller = new JAddressValidatorController();
	    controller.setAddressService (mockAddressService);
	}

	@Test
	public void testPost() {
	    when(mockAddressService.process(any(AddressRequest.class))).thenReturn(buildDefaultTestResponse());
	    AddressResponse response = controller.postRequest(buildTestRequest()).getBody();
	    assertThat(response.getP_Address(), is(equalTo(buildDefaultTestResponse().getP_Address())));
	}
	@Ignore
	@Test
	public void testPostKeyIsEmpty(){

        fail("Not yet implemented");
	}

    @Test
    public void testPostNullRequest(){
        AddressResponse response = controller.postRequest(null).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));      
    }
    
	@Test
	public void testPostInputAddressMissing(){
	    AddressRequest request = buildTestRequest();
	    request.Input_Address =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));	    
	}
	
    @Test
    public void testPostInputCityMissing(){
        AddressRequest request = buildTestRequest();
        request.Input_City =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));           
    }	
    
    @Test
    public void testPostInputStateMissing(){
        AddressRequest request = buildTestRequest();
        request.Input_State =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));        
    }  
    @Test
    public void testPostInputZipMissing(){
        AddressRequest request = buildTestRequest();
        request.Input_Zip =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));   
    }   
    @Test
    public void testPostInputCountryMissing(){
        AddressRequest request = buildTestRequest();
        request.Input_Country =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));   
    }   
    
    @Test
    public void testPostInputZip4Missing(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(buildDefaultTestResponse());
        AddressRequest request = buildTestRequest();
        request.Input_Zip4 =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo("")));        
    } 
    //"Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US"
    @Test
    public void testGetWithTestParams(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(buildDefaultTestResponse());

        AddressRequest req= buildTestRequest();
        AddressResponse response= controller.getByParams
                (req.Input_Zip, req.Input_Address, req.Input_City, req.Input_State, req.Input_Country)
                .getBody();

        assertThat(response.getP_Latitude(), is(equalTo(buildDefaultTestResponse().getP_Latitude())));
        assertThat(response.getP_Longitude(), is(equalTo(buildDefaultTestResponse().getP_Longitude())));
    }
    
    @Test
    public void testGetWithParamsMissingAddress(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "", "Input_City", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
    }
    @Test
    public void testGetWithParamsMissingCity(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "DES MOINES", "", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        
    }  
    @Test
    public void testGetWithParamsMissingState(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "DES MOINES", "Input_City", "", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        
    }
    @Test
    public void testGetWithParamsMissingZip(){
        AddressResponse response = controller.getByParams
                (null, "Input_Address", "Input_City", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        //Assert.That(result.P_ErrorMsg, Is.EqualTo("All fields required request."));
    }
    @Test
    public void testGetWithParamsMissingZip4(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(buildDefaultTestResponse());
        AddressRequest req= buildTestRequest();
        AddressResponse response= controller.getByParams
                (req.Input_Zip, req.Input_Address, req.Input_City, req.Input_State, req.Input_Country)
                .getBody();

        assertThat(response.getP_ErrorMsg(), is(equalTo(""))); 
        
    }
    
    @Test
    public void testGetwConnectionFailure(){
        /*
         *  testAddy.P_ErrorCode = "ERR";
            testAddy.P_ErrorMsg = "Connection Or Command failed. The connection cannot be opened because the connection timed out.";
         */
    }
}
