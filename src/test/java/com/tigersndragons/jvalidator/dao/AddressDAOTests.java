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
    public void setUp() throws Exception {
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
    
    private AddressResponse buildDefaultTestResponse(){
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
