package com.config;

import com.model.*;
import com.service.AddressService;
import org.immutables.mongo.repository.RepositorySetup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    public RepositorySetup repositorySetup() {
        return RepositorySetup.forUri(mongoUrl);
    }

    @Bean
    public UserRepository userRepository(RepositorySetup repositorySetup) {
        UserRepository userRepository = new UserRepository(repositorySetup);
        //unique constraint
        userRepository.index().withEmail().unique().ensure();
        return userRepository;
    }

    @Bean
    public UserDetailsRepository userDetailsRepository(RepositorySetup repositorySetup) {
        return new UserDetailsRepository(repositorySetup);
    }

    @Bean
    public AddressRepository addressRepository(RepositorySetup repositorySetup) {
        return new AddressRepository(repositorySetup);
    }

    @Bean
    public AddressService addressService() {
        return new AddressService();
    }

    @Bean
    public PostsRepository postsRepository(RepositorySetup repositorySetup) {
        return new PostsRepository(repositorySetup);
    }

    @Bean
    public PersonRepository personRepository(RepositorySetup repositorySetup) {
        return new PersonRepository(repositorySetup);
    }
}
