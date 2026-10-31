package org.example.expanse_tracking.Dto.Response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        T data,
        int status,
        String message,
        LocalDateTime timestamp
) {

    public static <T> ApiResponse<T> success(
            T data, int status, String message) {

        return new ApiResponse<>(
                data,
                status,
                message,
                LocalDateTime.now()
        );
    }

    public static ApiResponse<Void> error(
            int status, String message) {

        return new ApiResponse<>(
                null,
                status,
                message,
                LocalDateTime.now()
        );
    }
}