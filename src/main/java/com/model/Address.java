package com.model;

import org.immutables.mongo.Mongo;
import org.immutables.mongo.types.Id;
import org.immutables.value.Value;

@Value.Immutable
@Mongo.Repository
public abstract class Address {

    @Mongo.Id
    @Value.Default
    public Id addressId() {return Id.generate();}
    public abstract String addressLine1();
    public abstract String addressLine2();
}
