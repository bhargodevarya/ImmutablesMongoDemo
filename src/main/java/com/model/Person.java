package com.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.gson.Gson;
import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Currency;

import com.model.ImmutablePerson;

@Mongo.Repository("persons")
@Value.Immutable
@Gson.TypeAdapters
@JsonDeserialize(as = ImmutablePerson.class)
@JsonSerialize(as = ImmutablePerson.class)
public abstract class Person {

    @Mongo.Id
    //@Value.Auxiliary
    @Nullable
    public abstract Integer id();

    public abstract String name();

    public abstract Currency value();
}
