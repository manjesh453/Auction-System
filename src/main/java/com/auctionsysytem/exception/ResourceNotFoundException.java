package com.auctionsysytem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String filed;
    long fieldValue;

    public ResourceNotFoundException(String filed, long fieldValue) {
        super(String.format("%s not found with %s", filed, fieldValue));
        this.filed = filed;
        this.fieldValue = fieldValue;
    }
}
