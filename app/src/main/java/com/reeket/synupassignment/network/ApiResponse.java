package com.reeket.synupassignment.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResponse<T> {

    //ApiResponse is for convert any api response into generalize form

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public ApiResponse(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success (@Nullable T data) {
        return new ApiResponse<>(AuthStatus.SUCCESS, data, null);
    }

    public static <T> ApiResponse<T> error(@NonNull String msg, @Nullable T data) {
        return new ApiResponse<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> ApiResponse<T> loading(@Nullable T data) {
        return new ApiResponse<>(AuthStatus.LOADING, data, null);
    }

    public static <T> ApiResponse<T> retry(@Nullable T data) {
        return new ApiResponse<>(AuthStatus.RETRY, data, null);
    }

    public enum AuthStatus {SUCCESS, ERROR, LOADING, RETRY}

}
