package com.todorov.dateapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alex on 18.12.2016.
 */
@Path("test")
public class Test {
    @GET
    @Produces("text/shortdate")
    public Date testMethod(){
        return Calendar.getInstance().getTime();
    }
}
