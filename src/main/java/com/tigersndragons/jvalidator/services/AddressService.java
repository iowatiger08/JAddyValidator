package com.tigersndragons.jvalidator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tigersndragons.jvalidator.dao.AddressDAO;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;

@Component
public class AddressService implements IAddressService<AddressRequest, AddressResponse> {

    @Autowired
    private AddressDAO addressDAO ;
    
    public AddressResponse process(AddressRequest request) {
        AddressResponse aResponse =addressDAO.get(request.Input_Address, request.getInput_City(), request.Input_State, request.Input_Zip);
        if (aResponse == null || aResponse.getId() == null){
            //do google api validate
            aResponse.setP_Address(request.Input_Address);
            aResponse.setP_City(request.Input_City);
            aResponse.setP_State(request.Input_State);
            aResponse.setP_Zip(request.Input_Zip);
            addressDAO.create(aResponse);
        }
        return null;
    }
 
    public void setDataSource(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

}
