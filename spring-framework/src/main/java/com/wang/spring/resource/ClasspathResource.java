package com.wang.spring.resource;

import java.io.InputStream;

public class ClasspathResource implements Resource {

    private String location;

    public ClasspathResource(String location) {
        this.location = location;
    }

    public InputStream getResource() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
