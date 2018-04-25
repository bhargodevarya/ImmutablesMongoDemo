package com.service;

import com.google.common.util.concurrent.FutureCallback;
import com.model.Address;
import com.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddress() {
        List<Address> result = null;
        AddressRepository.Criteria criteria = addressRepository.criteria().addressLine1("7th main");
        try {
            result = addressRepository.find(criteria).fetchAll().get();
            //System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Address> getWithCallBack(FutureCallback<List<Address>> callback) {
        List<Address> addresses = null;
        try {
            addresses = addressRepository.findAll().fetchAll().addCallback(callback).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public void updateAddress() {

    }

}
