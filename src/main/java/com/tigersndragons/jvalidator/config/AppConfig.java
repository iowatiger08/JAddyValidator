package com.tigersndragons.jvalidator.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tigersndragons.jvalidator.dao.AddressDAO;
import com.tigersndragons.jvalidator.models.AddressRequest;
import com.tigersndragons.jvalidator.models.AddressResponse;
import com.tigersndragons.jvalidator.services.AddressService;
import com.tigersndragons.jvalidator.services.IAddressService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tigersndragons.jvalidator" )
public class AppConfig {
    @Bean
    public BasicDataSource getDefaultDataSource(){
        BasicDataSource defaultds = new org.apache.commons.dbcp.BasicDataSource();
        defaultds.setDriverClassName("com.mysql.jdbc.Driver");
        defaultds.setPassword("easypassword");
        defaultds.setUrl("jdbc:mysql://localhost:3306/addressvalidation");
        defaultds.setUsername("addressUser");
        defaultds.setInitialSize(30);
        defaultds.setPoolPreparedStatements(true);
        return defaultds;

    }
    @Bean
    public DataSource dataSource(){
        try {
            JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
            factoryBean.setDefaultObject(getDefaultDataSource());
            factoryBean.setExpectedType(DataSource.class);
            factoryBean.setJndiName("jdbc/addressvalidation");
            factoryBean.setResourceRef(true);
            factoryBean.afterPropertiesSet();
            return (DataSource) factoryBean.getObject();
        }catch(Exception e){
            throw new BeanCreationException("dataSource", e);
        }
    }
    /*
    @Bean
    public AddressDAO addressDAO(){
        AddressDAO dao = new AddressDAO();
        return dao;
    }
    
    @Bean 
    public IAddressService<AddressRequest, AddressResponse> addressService(){
        IAddressService<AddressRequest, AddressResponse> addressService = new AddressService();
        addressService.setDataSource(addressDAO());
        return addressService;
    }

   @Bean 
    public JAddressValidatorController validatorController(){
        JAddressValidatorController validatorController = new JAddressValidatorController();
        validatorController.setAddressService(addressService());
        return validatorController;
    }*/

}
