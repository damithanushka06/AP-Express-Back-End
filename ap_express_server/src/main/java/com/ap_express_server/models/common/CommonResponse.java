package com.ap_express_server.models.common;

public class CommonResponse {
    private String value;

    public CommonResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
