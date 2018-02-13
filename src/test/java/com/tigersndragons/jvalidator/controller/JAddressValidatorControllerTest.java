package com.tigersndragons.jvalidator.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
import com.tigersndragons.jvalidator.controller.JAddressValidatorController;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;
import com.tigersndragons.jvalidator.services.IAddressService;

public class JAddressValidatorControllerTest extends BaseTestCase{

    private JAddressValidatorController controller;
    public static String ALL_FIELDS_REQUIRED="All fields required request.";
    private String DefaultGetUrlwParams = "Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US";
            //"http://dev01/Address/index/new-client?Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US"
    @Mock
    private IAddressService<AddressRequest,AddressResponse> mockAddressService ;
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    controller = new JAddressValidatorController();
	    controller.setAddressService (mockAddressService);
	}

	@Test
	public void testPost() {
	    when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());
	    AddressResponse response = controller.postRequest(BuildTestRequest()).getBody();
	    assertThat(response.getP_Address(), is(equalTo(BuildDefaultTestResponse().getP_Address())));
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
	    AddressRequest request = BuildTestRequest();
	    request.Input_Address =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));	    
	}
	
    @Test
    public void testPostInputCityMissing(){
        AddressRequest request = BuildTestRequest();
        request.Input_City =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));           
    }	
    
    @Test
    public void testPostInputStateMissing(){
        AddressRequest request = BuildTestRequest();
        request.Input_State =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));        
    }  
    @Test
    public void testPostInputZipMissing(){
        AddressRequest request = BuildTestRequest();
        request.Input_Zip =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));   
    }   
    @Test
    public void testPostInputCountryMissing(){
        AddressRequest request = BuildTestRequest();
        request.Input_Country =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));   
    }   
    
    @Test
    public void testPostInputZip4Missing(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());
        AddressRequest request = BuildTestRequest();
        request.Input_Zip4 =null;
        AddressResponse response = controller.postRequest(request).getBody();
        assertThat(response.getP_ErrorMsg(), nullValue(String.class));        
    } 
    //"Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US"
    @Test
    public void testGetWithTestParams(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());

        AddressRequest req= BuildTestRequest();
        AddressResponse response= controller.getByParams
                (req.Input_Zip, req.Input_Address, req.Input_City, req.Input_State, req.Input_Country)
                .getBody();

        assertThat(response.getP_Latitude(), is(equalTo(BuildDefaultTestResponse().getP_Latitude()))); 
        assertThat(response.getP_Longitude(), is(equalTo(BuildDefaultTestResponse().getP_Longitude()))); 
    }
    
    @Test
    public void TestGetWithParamsMissingAddress(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "", "Input_City", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
    }
    @Test
    public void TestGetWithParamsMissingCity(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "DES MOINES", "", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        
    }  
    @Test
    public void TestGetWithParamsMissingState(){
        AddressResponse response = controller.getByParams
                ("Input_Zip", "DES MOINES", "Input_City", "", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        
    }
    @Test
    public void TestGetWithParamsMissingZip(){
        AddressResponse response = controller.getByParams
                (null, "Input_Address", "Input_City", "Input_State", "Input_Country")
                .getBody();
        assertThat(response.getP_ErrorMsg(), is(equalTo(ALL_FIELDS_REQUIRED)));  
        //Assert.That(result.P_ErrorMsg, Is.EqualTo("All fields required request."));
    }
    @Test
    public void TestGetWithParamsMissingZip4(){
        when(mockAddressService.process(any(AddressRequest.class))).thenReturn(BuildDefaultTestResponse());
        AddressRequest req= BuildTestRequest();
        AddressResponse response= controller.getByParams
                (req.Input_Zip, req.Input_Address, req.Input_City, req.Input_State, req.Input_Country)
                .getBody();

        assertThat(response.getP_ErrorMsg(), nullValue(String.class)); 
        
    }
    
    @Test
    public void testGetwConnectionFailure(){
        /*
         *  testAddy.P_ErrorCode = "ERR";
            testAddy.P_ErrorMsg = "Connection Or Command failed. The connection cannot be opened because the connection timed out.";
         */
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
                anAddress.setP_Address("666 Grand Ave".toUpperCase());
                anAddress.setP_City ( "Des Moines".toUpperCase());
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
