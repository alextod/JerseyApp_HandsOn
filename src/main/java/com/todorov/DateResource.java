package com.todorov;

import com.todorov.model.MyDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Alex on 18.12.2016.
 */
@Path("date/{dateString}")
public class DateResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRequestedDate(@PathParam("dateString")MyDate myDate){
        return  "Got " + myDate.toString();
    }
}
