package com.ap_express_server.common_utitlity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;

    public CustomException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
