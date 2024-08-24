package com.roomcatcher.RoomCatcher.global.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SuccessStatusResponse<T>(
    int code,
    String message,
    T data
) {
    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessStatusResponse<>(successMessage.getCode(), successMessage.getMessage(), data);
    }

    public static SuccessStatusResponse<Void> of(SuccessMessage successMessage) {
        return new SuccessStatusResponse<>(successMessage.getCode(), successMessage.getMessage(), null);
    }

    public static <T> SuccessStatusResponse<Map<String, T>> of(SuccessMessage successMessage, String key, T data) {
        return new SuccessStatusResponse<>(successMessage.getCode(), successMessage.getMessage(), Map.of(key, data));
    }

    public static <T> SuccessStatusResponse<T> of(ErrorMessage errorMessage, T data) {
        return new SuccessStatusResponse<>(errorMessage.getCode(), errorMessage.getMessage(), data);
    }

    public static <T> SuccessStatusResponse<Void> of(ErrorMessage errorMessage) {
        return new SuccessStatusResponse<>(errorMessage.getCode(), errorMessage.getMessage(), null);
    }
}
