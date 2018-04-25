package com.model;

import org.immutables.mongo.Mongo;
import org.immutables.mongo.types.Id;
import org.immutables.value.Value;

@Value.Immutable
@Mongo.Repository("books")
public abstract class Book {
    @Mongo.Id
    @Value.Default
    public Id id() {return Id.generate();}
    public abstract String title();
    public abstract Author author();
}
