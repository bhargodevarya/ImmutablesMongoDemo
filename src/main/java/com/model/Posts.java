package com.model;

import org.immutables.mongo.Mongo;
import org.immutables.mongo.types.Id;
import org.immutables.value.Value;

import java.util.Arrays;
import java.util.List;

@Value.Immutable
@Mongo.Repository("posts")
public interface Posts {
    @Mongo.Id
    Id id();
    String body();
    String permalink();
    String author();
    String title();
    String[] tags();
    List<Comment> comments();
}
