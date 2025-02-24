package com.example.SS.demo.Config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

@Data
@Builder
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private String responseCode;
    private T data;

    public GenericResponse(boolean success, String message, String responsecode, T data) {
        this.success = success;
        this.message = message;
        this.responseCode = responsecode;
        this.data = data;
    }

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .responseCode("200")
                .success(true)
                .message("SUCCESS!")
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> failed(T data, String responseCode, String msg) {
        return GenericResponse.<T>builder()
                .responseCode(responseCode)
                .message(msg)
                .success(false)
                .data(data)
                .build();
    }

}
