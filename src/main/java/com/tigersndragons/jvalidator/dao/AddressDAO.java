package com.tigersndragons.jvalidator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.tigersndragons.jvalidator.models.AddressResponse;

@Repository
public class AddressDAO {
    
    public AddressDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static List<AddressResponse> addresses;
    {
        addresses = new ArrayList<AddressResponse>();
        Double latitude = 41.58751;
        Double longitude = -93.62614;
        addresses.add(new AddressResponse(1L, "666 GRAND AVE", "DES MOINES", "", "" ,latitude, longitude, "C", "IA", "US" ,"50309","2506"));
        Double latitude2 = 41.5541486;
        Double longitude2 = -93.6329736;
        addresses.add(new AddressResponse(2L, "3308 SW 12TH ST", "DES MOINES", "", "" ,latitude2, longitude2, "C", "IA", "US" ,"50315","0000"));
        Double latitude3 = 41.554155;
        Double longitude3 = -93.630875;
        addresses.add(new AddressResponse(3L, "5201 PARK AVENUE", "DES MOINES", "", "" ,latitude3, longitude3, "C", "IA", "US" ,"50317","0000"));

    }
  

    public List<AddressResponse> list() {
        return addresses;
    }
    

    public AddressResponse get(Long id) {

        /*for (AddressResponse c : addresses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }*/
        
        return mappedList().get(id);
    }

    public AddressResponse create(AddressResponse newAddress) {
        newAddress.setId(System.currentTimeMillis());
        addresses.add(newAddress);
        return newAddress;
    }
    /*
    public Long delete(Long id) {

        for (AddressResponse addy : addresses) {
            if (addy.getId().equals(id)) {
                addresses.remove(addy);
                return id;
            }
        }

        return null;
    }*/
    //Guava style
    private Map<Long, AddressResponse> listAsMap(List<AddressResponse> addresses) {
        return Maps.uniqueIndex(addresses, c -> c.getId());
    }
    //Java 8 style
    public Map<Long, AddressResponse> mappedList() {
        return this.list().stream().collect(Collectors.toMap(AddressResponse::getId , a -> a));

    }


    public AddressResponse get(String input_Address, String input_City, String input_State, String input_Zip) {
       
        AddressResponse filtered = addresses.stream()
                .filter(a -> input_Address.equals(a.getP_Address()))
                .filter(a -> input_City.equals(a.getP_City()))
                .filter(a -> input_State.equals(a.getP_State()))
                .filter(a -> input_Zip.equals(a.getP_Zip()))
                .findAny()
                .orElse(null); 
        return filtered;
    }
}
