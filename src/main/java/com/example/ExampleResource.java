package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        final var resourceAsStream = ExampleResource.class.getResourceAsStream("/git.properties");
        final var properties = new Properties();
        try {
            System.out.println("resourceAsStream = " + resourceAsStream);
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("properties = " + properties);
        final var commitId = properties.getProperty("git.commit.id");
        var closestTagName = properties.getProperty("git.closest.tag.name");
        if(closestTagName.equals("")) closestTagName = "no tag";
        return "Hello! commit id: " + commitId + ", closest tag: " + closestTagName;
    }
}