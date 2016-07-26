package com.conflux.finflux.offline.exception;

import com.conflux.finflux.util.exception.CustomException;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class DateRangeException extends CustomException {

    public DateRangeException(String resource, String message) {
        super(resource, message);
    }
}
