package net.huray.backend.minuting.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataResponse<T> {

    private final T data;

    public static <T> DataResponse<T> from(T data) {
        return new DataResponse<>(data);
    }
}
