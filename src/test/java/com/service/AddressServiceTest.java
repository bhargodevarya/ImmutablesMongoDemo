package com.service;

import com.Config;
import com.model.Address;
import com.model.AddressRepository;
import com.model.ImmutableAddress;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@Profile("test")
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    private static AddressRepository.Criteria criteria;

    @BeforeClass
    public static void setUp() {

    }

    @Before
    public void beforeTest() {
        addressRepository.insert(ImmutableAddress.builder().addressLine1("7th main").addressLine2("add2").build());
    }

    @Test
    public void test() {
        List<Address> result = addressService.getAddress();
        org.springframework.util.Assert.notNull(result, "result should not be null");
    }
}
