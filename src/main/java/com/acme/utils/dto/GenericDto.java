package com.acme.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GenericDto<T> {

    private T payload;
    private String errorCode;
    private String errorMessage;


    public static <T> GenericDto<T> success(T payload) {
        return GenericDto.<T>builder()
                .payload(payload)
                .build();
    }

    public static <T> GenericDto<T> error(String errorMessage) {
        return GenericDto.<T>builder()
                .errorMessage(errorMessage)
                .build();
    }
}
