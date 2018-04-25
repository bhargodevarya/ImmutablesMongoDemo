package com;

import com.github.fakemongo.Fongo;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.GsonBuilder;
import com.model.AddressRepository;
import com.model.GsonAdaptersAddress;
import com.mongodb.FongoDB;
import com.service.AddressService;
import org.immutables.mongo.repository.RepositorySetup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("test")
public class Config {

    @Bean
    public RepositorySetup repositorySetup(Fongo fongo) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new GsonAdaptersAddress());
        return RepositorySetup.builder().database(new FongoDB(fongo, "testDB"))
                .gson(gsonBuilder.create()).executor(MoreExecutors.newDirectExecutorService()).build();
    }

    @Bean
    public Fongo fongo() {
        return new Fongo("test");
    }

    @Bean
    public AddressRepository addressRepository(RepositorySetup repositorySetup) {
        return new AddressRepository(repositorySetup);
    }

    @Bean
    public AddressService addressService() {
        return new AddressService();
    }
}
