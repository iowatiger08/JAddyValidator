package com.tigersndragons.jvalidator.services;

import org.springframework.stereotype.Component;

import com.tigersndragons.jvalidator.dao.AddressDAO;

@Component
public interface IAddressService<T,R> {
    
    R process(T request);

    void setDataSource(AddressDAO addressDAO);

}
