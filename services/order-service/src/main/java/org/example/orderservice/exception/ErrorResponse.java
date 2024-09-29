package org.example.orderservice.exception;


import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}