package com.tigersndragons.jvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;
import com.tigersndragons.jvalidator.services.IAddressService;

@RestController
@RequestMapping("/Address/index")
public class JAddressValidatorController {
    @Autowired
    private IAddressService<AddressRequest, AddressResponse> addressService;
    public static String ALL_FIELDS_REQUIRED="All fields required request.";

    /*
     * public String index(){ return "J Address Validator "; }
     */

    @PostMapping(value = "/new-client")//, method = RequestMethod.POST)
    public ResponseEntity<AddressResponse> postRequest(  @RequestBody AddressRequest aRequest) {
       
        if (!validateInput(aRequest)){
            return new ResponseEntity<AddressResponse>(sendInvalidResponse(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AddressResponse>( addressService.process(aRequest), HttpStatus.OK);     
    }
    
    private AddressResponse sendInvalidResponse(){
        AddressResponse response = new AddressResponse();
        response.setP_ErrorCode("ERR");
        response.setP_ErrorMsg(ALL_FIELDS_REQUIRED);
        return response;
    }

    //private String DefaultGetUrlwParams = "Input_Zip=50309&Input_Address=666%20Grand%20Avenue&Input_City=Des%20Moines&Input_State=IA&Input_Country=US";
    @GetMapping(value = "/new-client")//, method=RequestMethod.GET)
    public ResponseEntity<AddressResponse>  getByParams(
            @RequestParam("Input_Zip") String inputZip,
            @RequestParam("Input_Address") String inputAddress,
            @RequestParam("Input_City") String inputCity,
            @RequestParam("Input_State") String inputState,
            @RequestParam("Input_Country") String inputCountry
            ){
        AddressRequest request = validateParamInput(inputZip, inputAddress, inputCity, inputState, inputCountry);

        if (request==null){
            return new ResponseEntity<AddressResponse>(sendInvalidResponse(), HttpStatus.BAD_REQUEST);          
        }
        return new ResponseEntity<AddressResponse>(addressService.process(request), HttpStatus.OK);
    }

    private AddressRequest validateParamInput(String inputZip, String inputAddress, String inputCity,
            String inputState, String inputCountry) {
        AddressRequest request = new AddressRequest();
        request.Input_Address = inputAddress;
        request.Input_City = inputCity;
        request.Input_State = inputState;
        request.Input_Country = inputCountry;
        request.Input_Zip = inputZip;
        
        return validateInput(request)? request:null;
    }

    private boolean validateInput(AddressRequest aRequest) {
        return (aRequest != null)
                && !Strings.isNullOrEmpty(aRequest.Input_Address)
                && !Strings.isNullOrEmpty(aRequest.Input_City)
                && !Strings.isNullOrEmpty(aRequest.Input_State)
                && !Strings.isNullOrEmpty(aRequest.Input_Zip)
                && !Strings.isNullOrEmpty(aRequest.Input_Country)
                ;
    }

    public void setAddressService(IAddressService<AddressRequest, AddressResponse> addressService) {
        this.addressService = addressService;  
    }

}
