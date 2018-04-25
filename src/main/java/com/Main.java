package com;

import com.google.common.util.concurrent.FutureCallback;
import com.model.*;
import com.model.AddressRepository;
import com.model.ImmutableAddress;
import com.model.ImmutableUser;
import com.model.ImmutableUserDetails;
import com.model.PostsRepository;
import com.model.UserDetailsRepository;
import com.model.UserRepository;
import com.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Address userAddress = ImmutableAddress.builder().addressLine1("8th main").addressLine2("Bangalore").build();

        UserDetails userDetails = ImmutableUserDetails.builder().
                firstName("Ajay").lastName("Dev").isActive(Boolean.TRUE).passwordHash("hash").passwordSalt("salt").
                permanentAddress(userAddress).presentAddress(userAddress).
                build();

        User user = ImmutableUser.builder().email("barya3@sapient.com").userDetails(userDetails).build();

        Person person = ImmutablePerson.builder().name("bhargo").value(Currency.getInstance("INR")).build();

        personRepository.insert(person);

        //User user2 = ImmutableUser.builder().email("barya2@sapient.com").userDetails(userDetails).build();
        //insertDemo(user);
        //postFetchDemo();
    }

    private void insertDemo(User user) {
        userRepository.insert(user).addCallback(new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("added first");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void findCallBackDemo() {
        FutureCallback<List<Address>> callback = new FutureCallback<List<Address>>() {
            @Override
            public void onSuccess(@Nullable List<Address> result) {
                result.forEach(System.out::println);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        };
        addressService.getWithCallBack(callback);
    }

    private void getCriteriaDemo() {
        addressService.getAddress().forEach(System.out::println);
    }

    private void postFetchDemo() {
        postsRepository.findAll().orderByAuthor().fetchAll().addCallback(new FutureCallback<List<Posts>>() {
            @Override
            public void onSuccess(@Nullable List<Posts> result) {
                result.stream().map(res -> res.tags()).
                        flatMap(tagArr -> Arrays.stream(tagArr)).
                        forEach(System.out::println);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        System.out.println("find query has been fired");
    }
}
