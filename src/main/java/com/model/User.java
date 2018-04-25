package com.model;

import org.immutables.mongo.Mongo;
import org.immutables.mongo.types.Id;
import org.immutables.value.Value;

@Value.Immutable
@Mongo.Repository
public abstract class User {

    @Mongo.Id
    @Value.Default
    public Id userId() {return Id.generate();}
    public abstract String email();
    public abstract UserDetails userDetails();
}
