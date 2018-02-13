package com.tigersndragons.jvalidator.dao;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tigersndragons.jvalidator.BaseTestCase;
import com.tigersndragons.jvalidator.models.AddressResponse;

public class AddressDAOTests extends BaseTestCase{

    private AddressDAO addressDAO;
    
    @Before
    public void setUp() {
        addressDAO = new AddressDAO();
    }
    
    @Test
    public void GetSingleTest(){
        AddressResponse anAddress = addressDAO.get(1L);
        assertThat(anAddress.getId(), is (equalTo(1L)));
    }
    
    @Test
    public void createAddressTest(){
        AddressResponse anAddress = buildDefaultTestResponse();
        AddressResponse result = addressDAO.create(anAddress);
        assertThat(result.getId(), not(nullValue(Long.class)));
    }
    
    @Test
    public void getListTest(){
        List<AddressResponse> listOfAddresses = addressDAO.list();
        assertThat(listOfAddresses.size(), is(equalTo(3)));
    }
    
    @Test
    public void getListAsMapTest(){
        Map<Long, AddressResponse> mapOfAddresses = addressDAO.mappedList();
        assertThat(mapOfAddresses.size(), is(equalTo(3)));
    }
    @Test
    public void getListAsMapTestElement2and3AreDifferent(){
        Map<Long, AddressResponse> mapOfAddresses = addressDAO.mappedList();
        assertThat(mapOfAddresses.get(2L).getP_Address(), 
                is(not(equalTo(mapOfAddresses.get(3L).getP_Address()))));
    }
}
