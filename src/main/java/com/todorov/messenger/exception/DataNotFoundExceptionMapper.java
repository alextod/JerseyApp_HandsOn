package com.todorov.messenger.exception;

import com.todorov.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by atodorov on 10/5/2016.
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFountException> {

    @Override
    public Response toResponse(DataNotFountException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "http://java.net");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

}
