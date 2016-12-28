package com.todorov.dateapp.utils;

import com.todorov.dateapp.model.MyDate;

import javax.ws.rs.ext.ParamConverter;
import java.util.Calendar;

/**
 * Created by Alex on 18.12.2016.
 */
public class MyDateConverter implements ParamConverter<MyDate> {
    @Override
    public MyDate fromString(String s) {
        Calendar requestedDate = Calendar.getInstance();
        if("tomorrow".equalsIgnoreCase(s)){
            requestedDate.add(Calendar.DATE, 1);
        }
        else if ("yesterday".equalsIgnoreCase(s)){
            requestedDate.add(Calendar.DATE, -1);
        }
        MyDate myDate = new MyDate();
        myDate.setDate(requestedDate.get(Calendar.DATE));
        myDate.setMonth(requestedDate.get(Calendar.MONTH));
        myDate.setYear(requestedDate.get(Calendar.YEAR));
        return myDate;
    }

    @Override
    public String toString(MyDate myDate) {
        return myDate.toString();
    }
}
