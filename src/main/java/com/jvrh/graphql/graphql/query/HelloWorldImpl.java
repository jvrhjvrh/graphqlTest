package com.jvrh.graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldImpl implements GraphQLQueryResolver {
    public String helloWorld() {
        return "Hello World";
    }
}
