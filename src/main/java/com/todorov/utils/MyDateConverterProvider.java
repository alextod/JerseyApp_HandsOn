package com.todorov.utils;

import com.todorov.model.MyDate;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Alex on 18.12.2016.
 */

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(aClass.getName().equals(MyDate.class.getName())){
            return (ParamConverter<T>) new MyDateConverter();
        }
        return null;
    }
}
