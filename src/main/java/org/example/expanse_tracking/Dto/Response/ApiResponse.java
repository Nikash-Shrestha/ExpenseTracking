
package org.example.expanse_tracking.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private T data;
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(
            T data, int status, String message) {
        return new ApiResponse<>(
                data, status, message, LocalDateTime.now()
        );
    }

    public static ApiResponse<Void> error(
            int status, String message) {
        return new ApiResponse<>(
                null, status, message, LocalDateTime.now()
        );
    }
}
