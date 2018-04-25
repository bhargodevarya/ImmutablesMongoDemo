package com.model;

import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

@Value.Immutable
@Mongo.Repository
public interface Comment {

    String body();
    String email();
    String author();
}
