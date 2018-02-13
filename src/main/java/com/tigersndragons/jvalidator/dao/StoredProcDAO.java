package com.tigersndragons.jvalidator.dao;

import org.springframework.stereotype.Repository;

import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;

@Repository
public class StoredProcDAO {

    /**
     * mirrors current functions
     * @param addressRequest
     * @return
     */
    public AddressResponse get(AddressRequest addressRequest){
        //some stuff to call DB
        //get ResultSet and set to response
        return new AddressResponse();
    }
}
