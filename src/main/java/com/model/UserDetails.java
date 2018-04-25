package com.model;

import com.google.common.base.Optional;
import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

@Value.Immutable
@Mongo.Repository
//@Value.Style(strictBuilder = true) //cant reset collection attributes, cant reset other fields twice
public abstract class UserDetails {
    public abstract String firstName();
    //optional, therefore can be excluded while querying
    public abstract Optional<String> middleName();
    public abstract String lastName();
    public abstract String passwordHash();
    public abstract String passwordSalt();
    public abstract Boolean isActive();
    public abstract Address permanentAddress();
    public abstract Address presentAddress();
}
