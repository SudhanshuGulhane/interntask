package com.spring.loginexample.model;

//return response to user when a request is sent
//SUCCESS states that the request sent is successfully processed
//ALREADY_EXISTS : if a user sign ups with same username or email this response will be returned
//FAILURE

public enum Status {
    SUCCESS,
    ALREADY_EXISTS,
    FAILURE
}
