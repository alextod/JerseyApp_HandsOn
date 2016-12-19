package com.todorov;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("{pathParam}/myresource")
public class MyResource {

    private int count;

    @PathParam("pathParam") private String pathParamExample;
    @QueryParam("query") private String queryParamExample;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        count = count + 1;
        String str1 = "Got it! Called: " + count + " time(s)";
        String str2 = "PathParam " + pathParamExample + " and Query param " + queryParamExample;
        return str1 + "\n" + str2;
    }
}
